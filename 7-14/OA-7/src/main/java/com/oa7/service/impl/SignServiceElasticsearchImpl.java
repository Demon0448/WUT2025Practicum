package com.oa7.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa7.dao.EmpDao;
import com.oa7.dao.SignDao;
import com.oa7.pojo.Emp;
import com.oa7.pojo.Sign;
import com.oa7.pojo.SignCountDTO;
import com.oa7.pojo.SignDetailDTO;
import com.oa7.repository.SignElasticsearchRepository;
import com.oa7.service.SignService;
import com.oa7.util.DU;
import com.oa7.util.LocationUtil;
import com.oa7.util.RESP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class SignServiceElasticsearchImpl implements SignService {


    //TODO 先用mysql实现，后面再换成elasticsearch

    @Autowired
    private SignElasticsearchRepository signRepository;

    @Autowired
    private SignDao signDao;

    @Autowired
    private EmpDao empDao;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public RESP selectDaySignList(Integer currentPage, Integer pageSize) {

////        TODO 临时测试
//        //取出所有签到统计数据,全部存入 es
//        List<Sign> tmp=signDao.selectAll();
//        log.info("签到数据: {}", tmp);
//        for(Sign sign:tmp){
//            log.info("签到数据: {}", sign);
//            signRepository.save(sign);
//        }


        log.info("当前页: {}, 每页大小: {}", currentPage, pageSize);
        PageHelper.startPage(currentPage, pageSize, true);
        List<SignCountDTO> list = signDao.selectSignCountByDay();
        PageInfo<SignCountDTO> pageInfo = new PageInfo<>(list);
        log.info("查询到的签到统计数据: {}", list);



        return RESP.ok(pageInfo.getList(), pageInfo.getPageNum(), (int) pageInfo.getTotal());
    }

    // 获取当前员工签到记录
    @Override
    public RESP empSignList(HttpSession session) {

        System.out.println("获取当前员工签到记录");
        // 1. 获取当前员工数据
        Emp emp = (Emp) session.getAttribute("emp");
        if (emp == null) {
            return RESP.error("用户未登录");
        }
        // 2. 获取员工的当天的日期
        String today = DU.getNowSortString();

        List<Sign> list=new ArrayList<>();

        // 3. 查询当天打卡记录
        // 检查是否已经存在今日的签到记录（使用dateOnly字段）需要为当前员工创建签到任务
        List<Sign> TodayRecords = signRepository.findByNumberAndDateOnly(emp.getNumber(), today);
        System.out.println(TodayRecords.size());

        if (TodayRecords.isEmpty()) {
            // 只为当前员工创建今日的签到记录
            // 创建上午签到记录
            Sign morSign = createSign(emp.getNumber(), DU.getNowAM(), "未签到", "a");
            signRepository.save(morSign);

            // 创建下午签到记录
            Sign afterSign = createSign(emp.getNumber(), DU.getNowPM(), "未签到", "p");
            signRepository.save(afterSign);

            // 重新查询当天打卡记录
            list = signRepository.findByNumberAndDateOnly(emp.getNumber(), today);
        } else {
            list = TodayRecords;
        }
        // 补充员工信息（姓名、部门）
        list = AddEmpInfo(list);

        return RESP.ok(list);
    }

    /**
     * 创建签到记录
     */
    private Sign createSign(int empNumber, String signDate, String state, String type) {
        Sign sign = new Sign();
        sign.setId(UUID.randomUUID().toString());
        sign.setSignDate(signDate);
        sign.setNumber(empNumber);
        sign.setState(state);
        sign.setType(type);
        sign.setTimestamp(System.currentTimeMillis());
        sign.setDateOnly(dateFormat.format(new Date()));
        sign.setTag(0);
        return sign;
    }

    /**
     * 补充签到记录的员工信息（姓名、部门）
     */
    private List<Sign> AddEmpInfo(List<Sign> signs) {
        for (Sign sign : signs) {
            try {
                // 根据员工编号查询员工信息
                Emp emp = empDao.selectByEmpNumber(sign.getNumber());
                if (emp != null) {
                    sign.setName(emp.getName());
                    sign.setDept_name(emp.getDept_name());
                }
            } catch (Exception e) {
                // 如果查询失败，设置默认值
                sign.setName("未知员工");
                sign.setDept_name("未知部门");
            }
        }
        return signs;
    }



    //TODO 弃用

    @Override
    public RESP selectByPagehelper(int currentPage, int pageSize, HttpSession session) {
        return null;
    }

    //分页查询员工已签到记录 TODO 大动刀
    @Override
    public RESP selectByPage(int currentPage, int pageSize) {

        // 2. 使用 Elasticsearch 分页查询
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<Sign> page = signRepository.findAllByOrderByTimestampDesc(pageable);

        List<Sign> list = page.getContent();
        // 补充员工信息(补充对应的部门信息)
        list = AddEmpInfo(list);
        // 获取总条数
        long total = signRepository.count();
        return RESP.ok(list,currentPage, (int) total);
    }


    @Override
    public RESP updateState(Sign sign, HttpSession session, String coordinates) {
        try {
            Emp emp = (Emp) session.getAttribute("emp");
            if (emp == null) {
                return RESP.error("用户未登录");
            }
            // 设置员工编号
            sign.setNumber(emp.getNumber());
            // 查找今天该员工该类型的签到记录

            String today = DU.getNowSortString(); // 获取今天日期 yyyy-MM-dd

            // 先查询今天所有记录，然后筛选类型
            List<Sign> todayRecords = signRepository.findByNumberAndDateOnly(emp.getNumber(), today);
            System.out.println("签到的时候 查询记录");
            for (int i = 0; i <todayRecords.size(); i++) {
                System.out.println("员工编号: " + todayRecords.get(i).getNumber() +
                        ""+"签到时间: " + todayRecords.get(i).getSignDate() +
                        ""+"状态: " + todayRecords.get(i).getState());
            }

            Sign SignModer = null;
            // 查找对应类型的记录
            for (Sign record : todayRecords) {
                System.out.println(record.getType());
                if (sign.getType().equals(record.getType())) {
                    SignModer = record;
                    break;
                }
            }

            if (SignModer != null) {
                // 检查是否已经签到过
                if ("已签到".equals(SignModer.getState())) {
                    return RESP.error("今日已" + (sign.getType().equals("a") ? "签到" : "签退") + "，不可重复操作");
                }
                
                // 更新签到状态
                SignModer.setState("已签到");
                SignModer.setSignDate(DU.formatDateToString(new Date()));
                
                // 解析地理位置
                if (coordinates != null && !coordinates.isEmpty()) {
                    // 验证坐标格式
                    if (LocationUtil.isValidCoordinates(coordinates)) {
                        try {
                            String address = LocationUtil.getAddressFromCoordinates(coordinates);
                            SignModer.setSign_address(address);
                        } catch (Exception e) {
                            System.err.println("地址解析异常：" + e.getMessage());
                            SignModer.setSign_address("位置解析失败");
                        }
                    } else {
                        SignModer.setSign_address("坐标格式错误");
                    }
                } else {
                    SignModer.setSign_address("未获取到位置信息");
                }

                // 更新时间戳
                SignModer.setTimestamp(System.currentTimeMillis());
                SignModer.setDateOnly(today);

                // 补充员工信息
                SignModer.setName(emp.getName());
                SignModer.setDept_name(emp.getDept_name());

                signRepository.save(SignModer);
                return RESP.ok((sign.getType().equals("a") ? "签到" : "签退") + "成功");
            } else {
                return RESP.error("未找到今日的" + (sign.getType().equals("a") ? "签到" : "签退") + "任务，请联系管理员");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RESP.error("签到失败：" + e.getMessage());
        }
    }

    @Override
    public RESP getStatisticsChart() {


        //mysql 实现版本

//        // 得到最近十天的日期字符串
//        String[] lastTenDays = new String[5];
//        List<Integer> yesCountList = new ArrayList<>(); // 已签到人数
//        List<Integer> noCountList = new ArrayList<>();  // 未签到人数
//        List<Integer> totalList = new ArrayList<>();    // 需签到总人数
//
//
//        for (int i = 0; i < 5; i++) {
//            lastTenDays[i] = LocalDate.now().minusDays(i).toString();
//
//            SignCountDTO signCountDTO = signDao.selectByDay(lastTenDays[i]);
//            log.info("查询到的签到统计数据: {}", signCountDTO);
//
//            if (signCountDTO != null) {
//                yesCountList.add(signCountDTO.getYc());
//                noCountList.add(signCountDTO.getNc());
//                totalList.add(signCountDTO.getYc() + signCountDTO.getNc());
//            } else {
//                // 如果某天没有数据，默认值为0
//                yesCountList.add(0);
//                noCountList.add(0);
//                totalList.add(0);
//            }
//        }
//
//        //TODO: 添加返回数据
//        return RESP.ok(lastTenDays, yesCountList, noCountList, totalList);

        //  elasticsearch 实现版本
        // 获取今天的日期
        String today = DU.getNowSortString();
        // 获取最近5天的日期
        String[] lastTenDays = new String[5];
        List<Integer> yesCountList = new ArrayList<>(); // 已签到人数
        List<Integer> noCountList = new ArrayList<>();
        List<Integer> totalList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            lastTenDays[i] =LocalDate.now().minusDays(i).toString();
            //1 取出这一天签到记录
            //mysql版本
            //List<Sign> signList = signDao.selectByDate(lastTenDays[i]);
            //elasticsearch版本
            log.info("查询的日期: {}", lastTenDays[i]);
            List<Sign> signList = signRepository.findByDateOnly(lastTenDays[i]);
            log.info("查询到的签到数据: {}", signList);

            // 如果没有签到记录，默认值为0
            int yesCount = 0; // 已签到人数
            int noCount = 0;
            for(Sign sign : signList){
                //签到并且是上午
                if(sign.getType().equals("a")){
                    if(sign.getState().equals("已签到")){
                        yesCount++;
                    } else{
                        noCount++;
                    }
                }
            }
            yesCountList.add(yesCount);
            noCountList.add(noCount);
            totalList.add(yesCount + noCount);
            log.info("日期: {}, 已签到人数: {}, 未签到人数: {}, 总人数: {}", lastTenDays[i], yesCount, noCount, yesCount + noCount);

        }

        return RESP.ok(lastTenDays, yesCountList, noCountList, totalList);

    }

    @Override
    public RESP getDailyDetails(String date) {
        //TODO 先都使用mysql实现，后面再换成elasticsearch
        SignDetailDTO  signDetailDTO = new SignDetailDTO();
        int msc=0,muc=0,esc=0,euc=0,tsc=0,tuc=0;
        //1 取出这一天签到记录
        List<Sign> signList = signDao.selectByDate(date);
        //2 查询这一天的签到记录，更新signDetailDTO
        for(Sign sign : signList){
            if(sign.getState().equals("已签到")){
                if(sign.getType().equals("a")){
                    // 上午签到
                    msc++;
                }else{
                    // 下午签到
                    esc++;
                }
                tsc++;
            }else{
                if(sign.getType().equals("a")){
                    // 上午未签到
                    muc++;
                }else{
                    // 下午未签到
                    euc++;
                }
                tuc++;
            }
        }
        //3 设置signDetailDTO
        signDetailDTO.setDate(date);
        signDetailDTO.setMorningSignedCount(msc);
        signDetailDTO.setMorningUnsignedCount(muc);
        signDetailDTO.setEveningSignedCount(esc);
        signDetailDTO.setEveningUnsignedCount(euc);
        signDetailDTO.setTotalSignedCount(tsc);
        signDetailDTO.setTotalUnsignedCount(tuc);
        log.info("返回的签到统计数据: {}", signDetailDTO);
        //返回signDetailDTO
        return RESP.ok(signDetailDTO);
    }

    @Override
    public RESP getTodaySigned() {
        // 获取今天的日期
        String today = DU.getNowSortString();
        log.info("获取今天的签到记录，日期: {}", today);
        // 查询今天的签到记录数量 mysql
        List<Sign> list=signDao.selectYesTargetDayByPage(today);

        return RESP.ok(null,-1, (int) list.size());
    }
}
package com.oa2.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa2.service.SignService;
import com.oa2.dao.EmpDao;
import com.oa2.dao.SignDao;
import com.oa2.pojo.Emp;
import com.oa2.pojo.Sign;
import com.oa2.util.DU;
import com.oa2.util.IpToAddressUtil;
import com.oa2.util.LocationUtil;
import com.oa2.util.RESP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class SignServiceImpl implements SignService {
    @Autowired
    private SignDao signDao;

    @Autowired
    private EmpDao empDao;

    //查找员工今日的签到任务
    @Override
    public RESP empSignList(HttpSession session) {
        System.out.println("SignServiceImpl");
        //1  获取当前员工数据
        Emp emp = (Emp) session.getAttribute("emp");
        //2  获取员工的当天的日期
        String tody = DU.getNowSortString();
        //3  查询当天打卡记录
        List<Sign> list = signDao.selectEmpSign(emp, tody);

        //说明是第一个员工没有签到信息
        if (list.size() == 0){
            List<Integer> list1 = empDao.selectAllEmpNumber();//获取到所有员工的ID
            //4  创建员工打卡记录
            for (int id : list1) {
                Sign sign = new Sign();
                //员工当天的上午 下午  签到状态  a p
                sign.setSignDate(DU.getNowAM());
                sign.setNumber(id);
                sign.setState("未签到");
                sign.setType("a");
                signDao.addSign(sign);//循环插入每个员工为上午未签到状态

                sign.setSignDate(DU.getNowPM());
                sign.setType("p");
                signDao.addSign(sign);
            }
            list = signDao.selectEmpSign(emp, tody);
        }
        //   返回结果
        return RESP.ok(list);
    }

    @Override
    public RESP selectByPage(int currentPage, int pageSize, HttpSession session) {
        //1  查询当前员工信息
        Emp emp = (Emp) session.getAttribute("emp");

        int current = currentPage;
        currentPage = (currentPage - 1) * pageSize;//0   10

        List<Sign> list = signDao.SelectByPage(emp, currentPage, pageSize);

        //获取总条数(当前员工的所有签到记录 )
        int all = signDao.countSignByNumber(emp);

        return RESP.ok(list, current, all);
    }


    @Override
    public RESP selectByPagehelper(int current, int Size, HttpSession session) {
        System.out.println("使用PageHelper实现分页");
        //1使用Session获取当前用户信息
        Emp emp = (Emp) session.getAttribute("emp");
        //2 分页的实现
        PageHelper.startPage(current, Size, true);
        //3 调用持久层进行数据查询
        List<Sign> list = signDao.selectByPagehelper(emp);
        //将List对象转化为PageInfo对象
        PageInfo<Sign> data = new PageInfo<Sign>(list);
        System.out.println(data.getList());

        return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
    }


    //员工签到
    @Override
    public RESP updateState(Sign sign, HttpSession session, String cor) {
        System.out.println("地图的");

        //1 获取对应的地址(根据前端获取的经纬度  IP地址  服务器地址  来确认签到地址)
        //String address = IpToAddressUtil.getCityInfo(IpToAddressUtil.getV4IP(), "geocoder", cor);
        String address= LocationUtil.getAddressFromCoordinates(cor);
        log.info("address:"+address);
        //2  将地址 传递至sign类
        sign.setSign_address(address);
        int i = signDao.updateState(sign, DU.getNowString());

        Emp emp = (Emp) session.getAttribute("emp");
        //获取当前的时间
        String today = DU.getNowSortString();

        List<Sign> list = signDao.selectEmpSign(emp, today);

        if (i > 0) {
            return RESP.ok(list);
        }
        return null;
    }
}

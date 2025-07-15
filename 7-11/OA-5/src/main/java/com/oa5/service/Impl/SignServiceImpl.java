package com.oa5.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa5.dao.SignDao;
import com.oa5.pojo.Sign;
import com.oa5.pojo.SignCountDTO;
import com.oa5.service.SignService;
import com.oa5.util.DU;
import com.oa5.util.RESP;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class SignServiceImpl implements SignService {
    @Autowired
    private SignDao signDao;

    @Override
    public RESP selectDaySignList(Integer currentPage, Integer pageSize) {

        log.info("当前页: {}, 每页大小: {}", currentPage, pageSize);

        PageHelper.startPage(currentPage, pageSize, true);
        List<SignCountDTO> list = signDao.selectSignCountByDay();
        PageInfo<SignCountDTO> pageInfo = new PageInfo<>(list);

        log.info("查询到的签到统计数据: {}", list);

        //TODO
        // 这里可以根据业务需求进一步处理数据
        return RESP.ok(pageInfo.getList(), pageInfo.getPageNum(), (int) pageInfo.getTotal());
    }

    @Override
    public RESP selectYesByPage(Integer currentPage, Integer pageSize) {
        log.info("当前页: {}, 每页大小: {}", currentPage, pageSize);
        PageHelper.startPage(currentPage, pageSize, true);
        List<Sign> signList = signDao.selectYes();
        PageInfo<Sign> data = new PageInfo<>(signList);
        log.info("分页数据: {}", data);
        return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
    }

    @Override
    public RESP selectNoByPage(Integer currentPage, Integer pageSize) {
        log.info("当前页: {}, 每页大小: {}", currentPage, pageSize);
        PageHelper.startPage(currentPage, pageSize, true);
        List<Sign> signList = signDao.selectNo();
        PageInfo<Sign> data = new PageInfo<>(signList);
        log.info("分页数据: {}", data);
        return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
    }

    @Override
    public RESP selectToDayYesByPage(Integer currentPage, Integer pageSize) {
        log.info("当前页: {}, 每页大小: {}", currentPage, pageSize);
        PageHelper.startPage(currentPage, pageSize, true);
        String day = LocalDate.now().toString(); // 获取当前日期
        List<Sign> signList = signDao.selectYesTargetDayByPage(day);
        PageInfo<Sign> data = new PageInfo<>(signList);
        return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
    }

    @Override
    public RESP selectToDayNoByPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize, true);
        String today = LocalDate.now().toString();
        List<Sign> signList = signDao.selectNoTargetDayByPage(today);
        PageInfo<Sign> data = new PageInfo<>(signList);
        return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
    }

    @Override
    public RESP selectImgSignList(Integer currentPage, Integer pageSize) {
        // 得到最近十天的日期字符串
        String[] lastTenDays = new String[10];
        List<Integer> yesCountList = new ArrayList<>(); // 已签到人数
        List<Integer> noCountList = new ArrayList<>();  // 未签到人数
        List<Integer> totalList = new ArrayList<>();    // 需签到总人数
        //TODO 一页十组，通过LocalDate.now().minusDays(i).toString()进行日期减法，得到最近十天
        int start = (currentPage - 1) * pageSize;
        for (int i = start; i < 10+start; i++) {
            lastTenDays[i-start] = LocalDate.now().minusDays(i).toString();

            SignCountDTO signCountDTO = signDao.selectByDay(lastTenDays[i-start]);
            log.info("查询到的签到统计数据: {}", signCountDTO);

            if (signCountDTO != null) {
                yesCountList.add(signCountDTO.getCountYes());
                noCountList.add(signCountDTO.getCountNo());
                totalList.add(signCountDTO.getCountYes() + signCountDTO.getCountNo());
            } else {
                // 如果某天没有数据，默认值为0
                yesCountList.add(0);
                noCountList.add(0);
                totalList.add(0);
            }
        }

        //TODO: 添加返回数据
        return RESP.ok(lastTenDays, yesCountList, noCountList, totalList);
    }


    @Override
    public RESP updateStateYes(Sign sign, int currentPage, int pageSize) {
        //查询签到记录就不查了

        sign.setState("未签到");
        sign.setSign_address("已驳回");
        String date= DU.getNowString();
        log.info("date="+date);
        int update = signDao.updateState(sign, date);
        if (update <= 0) {
            return null;
        }
        log.info("更新成功");
        return selectYesByPage(currentPage, pageSize);
    }

    @Override
    public RESP updateStateNo(Sign sign, Integer currentPage, Integer pageSize) {
        sign.setState("已签到");
        sign.setSign_address("补签成功");
        String date= DU.getNowString();
        log.info("date="+date);
        int update = signDao.updateState(sign, date);
        if (update <= 0) {
            return null;
        }
        return selectNoByPage(currentPage,pageSize);
    }

    @Override
    public RESP searchByEmployeeNumberAndState(String employeeNumber,String  state ,Integer currentPage, Integer pageSize) {
        log.info("当前页: {}, 每页大小: {}, 工号: {}", currentPage, pageSize, employeeNumber);
        PageHelper.startPage(currentPage, pageSize, true);
        List<Sign> signList = signDao.selectByEmployeeNumberAndState(employeeNumber, state);
        PageInfo<Sign> data = new PageInfo<>(signList);
        return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
    }
}

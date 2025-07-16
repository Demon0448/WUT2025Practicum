package com.oa7.service;


import com.oa7.pojo.Sign;
import com.oa7.util.RESP;

import javax.servlet.http.HttpSession;


public interface SignService {

    RESP selectDaySignList(Integer currentPage, Integer pageSize);


    //查找员工今日签到任务
    public RESP empSignList(HttpSession session);

    //分页查询   TODO 先等等
    public RESP selectByPage(int currentPage,int pageSize);

    //使用pagehelper实现用户签到分页查询
    public RESP selectByPagehelper(int currentPage,int pageSize,HttpSession session);

    //员工签到
    RESP updateState(Sign sign , HttpSession session, String cor);

    RESP getStatisticsChart(int currentPage, int pageSize);

    RESP getDailyDetails(String date);

    RESP getTodaySigned();

    RESP getUnsigned(int currentPage, int pageSize);

    RESP getSigned(int currentPage, int pageSize);

    RESP rejectSign(Integer id);

    RESP approveSign(Integer id);

    RESP loadSignData();

    RESP searchByEmployeeNumberAndState(String employeeNumber, String state, Integer currentPage, Integer pageSize);

}

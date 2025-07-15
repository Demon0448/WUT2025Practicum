package com.oa7.service;


import com.oa7.pojo.Sign;
import com.oa7.util.RESP;

import javax.servlet.http.HttpSession;


public interface SignService {

    //查找员工今日签到任务
    public RESP empSignList(HttpSession session);

    //分页查询
    public RESP selectByPage(int currentPage,int pageSize,HttpSession session);

    //使用pagehelper实现用户签到分页查询
    public RESP selectByPagehelper(int currentPage,int pageSize,HttpSession session);

    //员工签到
    RESP updateState(Sign sign , HttpSession session, String cor);







}

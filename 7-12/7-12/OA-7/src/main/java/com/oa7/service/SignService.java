package com.oa7.service;

import com.oa7.pojo.Sign;
import com.oa7.util.RESP;

import javax.servlet.http.HttpSession;

public interface SignService {
    //查询某个员工所有考勤信息
    RESP selectByPage(int a , int b , HttpSession session);

    //查询已签到的考勤信息
    RESP selectYesByPage(int a , int b);

    //查询未签到的考勤信息
    RESP selectNoByPage(int a , int b);

    //查询所有考勤信息
    RESP selectAllByPage(int a , int b);

    //查询今日已签到的考勤信息
    RESP selectToDayYesByPage(int a , int b);

    //查询今日未签到的考勤信息
    RESP selectToDayNoByPage(int a , int b);

    //查询今日的考勤信息
    RESP selectDaySignList(int a , int b);

    //查询某个员工今日考勤信息
    RESP empSignList(HttpSession session);

    //更新员工考勤状态
    RESP updateState(Sign sign , HttpSession session);

    //补签
    RESP updateStateYes(Sign sign , int a , int b);

    //驳回
    RESP updateStateNo(Sign sign , int a , int b);


    void deleteSign(String beganDate,String endDate);

    RESP selectImgSignList();


}

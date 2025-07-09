package com.oa2.service;

import com.oa2.pojo.Emp;
import com.oa2.pojo.Sign;
import com.oa2.util.RESP;

import javax.servlet.http.HttpSession;


public interface SignService {
    /**
     * 用户签到日期查询
     * @param session
     * @return
     */
    public RESP empSignList(HttpSession session);


    RESP updateState(Sign sign, HttpSession session, String cor);
}

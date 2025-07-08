package com.oa2.service;

import com.oa2.pojo.Emp;
import com.oa2.util.RESP;

import javax.servlet.http.HttpSession;

public interface EmpService {


    Emp login(Emp emp, HttpSession session);

    RESP updateInfo(Emp emp, HttpSession session);

    RESP getInfo(HttpSession session);

    String updateEmpPwd(Emp emp, String oldPwd);


}

package com.oa2.service;

import com.oa2.pojo.Emp;
import com.oa2.util.RESP;

import javax.servlet.http.HttpSession;

public interface EmpService {

    /**
     * 员工登录
     * @param emp
     * @param session
     * @return
     */
    public String emplogin(Emp emp, HttpSession session);

    /**
     *  修改密码
     * @param
     * @return
     */
    public String updateEmpPwd(Emp emp,String oldpwd);

    public RESP updateInfo(Emp emp,HttpSession session);
}

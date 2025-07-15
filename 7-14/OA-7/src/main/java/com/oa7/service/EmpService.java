package com.oa7.service;



import com.oa7.pojo.Emp;
import com.oa7.util.RESP;

import javax.servlet.http.HttpSession;

public interface EmpService {

    //员工登录
    public String emplogin(Emp emp , HttpSession session);

    //更新密码
    public String updateEmpPwd(Emp emp,String oldpwd);

    //修改用户
    public RESP updateInfo(Emp emp, HttpSession session);

    RESP selectByPage(int currentPage, int pageSize);

    RESP selectTodaySigned(int currentPage, int pageSize);




    RESP selectAllDept();

    RESP selectAllDuty();

    RESP addEmp(Emp emp);

    RESP updateEmp(Emp emp, Integer currentPage, Integer pageSize);

    RESP deleteEmp(int number);
}

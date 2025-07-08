package com.oa2.service.impl;

import com.oa2.service.EmpService;
import com.oa2.dao.EmpDao;
import com.oa2.pojo.Emp;
import com.oa2.util.RESP;
import com.liuvei.common.SysFun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;

    @Override
    public Emp login(Emp emp, HttpSession session) {
        System.out.println("EmpServiceImpl.login()");

        //调用dao层的登录方法
        Emp emp1 = empDao.selectByNumber(emp);

        //判断登录是否成功
        if (emp1 == null) {
            return null;
        }
        //判断密码是否正确
        if (!SysFun.md5(emp.getPwd()).equals(emp1.getPwd())) {
            return null;
        }

        //将用户信息存入session
        session.setAttribute("emp", emp1);

        return emp1;
    }

    @Override
    public RESP updateInfo(Emp emp, HttpSession session) {
        int i=empDao.updateEmp(emp);
        if(i>0){
            //修改后重新获取用户信息
            Emp emp1 = empDao.selectByNumber(emp);
            //将修改后的用户信息存入session
            session.setAttribute("emp", emp1);
            return RESP.ok(emp1);
        }
        return null;
    }

    @Override
    public RESP getInfo(HttpSession session) {
        //从session中获取用户信息
        Emp emp = (Emp) session.getAttribute("emp");
        if (emp == null) {
            return null;
        }

        //调用dao层的查询方法
        Emp emp1 = empDao.selectByNumber(emp);
        if (emp1 == null) {
            return null;
        }

        return RESP.ok(emp1);
    }

    @Override
    public String updateEmpPwd(Emp emp, String oldPwd) {
        //调用dao层的查询方法
        Emp emp1 = empDao.selectByNumber(emp);
        if (emp1 == null) {
            return "false";
        }

        //判断旧密码是否正确
        if (!SysFun.md5(oldPwd).equals(emp1.getPwd())) {
            return "false";
        }

        //修改密码
        emp.setPwd(SysFun.md5(emp.getPwd()));
        int i = empDao.updateEmpPwd(emp);
        if (i > 0) {
            return "true";
        }
        return "false";
    }




}

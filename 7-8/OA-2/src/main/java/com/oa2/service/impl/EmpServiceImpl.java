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
public class EmpServiceImpl implements EmpService{
    @Autowired
    private EmpDao empDao;

    @Override
    public String emplogin(Emp emp, HttpSession session) {
        Emp emp1 = empDao.selectByNumber(emp);
        //判断用户是否存在(Number)
        if(emp1!=null){
            if(emp1.getPwd().equals(SysFun.md5(emp.getPwd()))){//4297f44b13955235245b2497399d7a93
                //密码匹配 登录成功
                session.setAttribute("emp",emp1);
                return "true";
            }
        }
        return "false";
    }

    @Override
    public String updateEmpPwd(Emp emp, String oldpwd) {
        //1  通过emp对象调用MyBatsi 返回员工对象
         Emp Loginemp= empDao.selectByNumber(emp);//MD5加密

         if(Loginemp!=null){
             //2  如果用户存在 将旧的密码与数据库中的密码进行匹配
             if(Loginemp.getPwd().equals(SysFun.md5(oldpwd))){//1234
                 //3  如果匹配成功 将新的密码（MD5加密） 存入到数据库中
                 emp.setPwd(SysFun.md5(emp.getPwd()));
                 int i = empDao.updateEmpPwd(emp);
                 if(i>0){
                     return "true";
                 }
             }
         }
        return "false";
    }

    @Override
    public RESP updateInfo(Emp emp, HttpSession session) {
        //执行员工信息修改
        int i= empDao.updateEmp(emp);
        if(i>0){
            //将修改后的新的用户对象 存入session中
           Emp emp1= empDao.selectByNumber(emp);
           session.setAttribute("emp",emp1);
           return RESP.ok(emp1);
        }
        return null;
    }
}

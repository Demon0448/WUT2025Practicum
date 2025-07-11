package com.oa5.service.Impl;

import com.oa5.service.AdmService;
import com.oa5.dao.AdmDao;
import com.oa5.pojo.Admin;
import com.liuvei.common.SysFun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
public class AdmServiceImpl implements AdmService {

    @Autowired
    private AdmDao admDao;

    //管理员登录
    @Override
    public String login(Admin admin, HttpSession session) {
        Admin admin1 = admDao.selectByName(admin);
        if (admin1 != null) {
            if (admin1.getPwd().equals(SysFun.md5(admin.getPwd()))) {
                session.setAttribute("admin",admin1);
                return "true";
            }
        }
        return "false";
    }

    //管理员注册
    @Override
    public String register(Admin admin) {
        Admin admin1 = admDao.selectByName(admin);
        if (admin1 == null) {
            admin.setPwd(SysFun.md5(admin.getPwd()));
            int a = admDao.insertAdm(admin);
            if (a > 0) {
                return "true";
            }
        }
        return "false";
    }
}

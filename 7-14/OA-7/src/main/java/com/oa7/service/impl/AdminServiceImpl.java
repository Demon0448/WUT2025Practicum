package com.oa7.service.impl;


import com.liuvei.common.SysFun;
import com.oa7.dao.AdminDao;
import com.oa7.pojo.Admin;
import com.oa7.service.AdmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
public class AdminServiceImpl implements AdmService {
    @Autowired
    private AdminDao adminDao;


    @Override
    public String login(Admin admin, HttpSession session) {
        // 管理员登录
        log.info("管理员登录请求: {}", admin);
        //判断数据库中是否有该管理员
        Admin admin1 = adminDao.selectByName(admin);
        log.info("管理员查询结果: {}", admin1);

        if (admin1 != null) {
            //判断密码是否正确
            if (admin1.getPwd().equals(SysFun.md5(admin.getPwd()))) {
                session.setAttribute("admin", admin1);
                return "true";
            }
        }
        return "false";
    }

    @Override
    public String register(Admin admin) {
        Admin admin1 = adminDao.selectByName(admin);
        if (admin1 == null) {
            admin.setPwd(SysFun.md5(admin.getPwd()));
            int a = adminDao.insertAdm(admin);
            if (a > 0) {
                return "true";
            }
        }
        return "false";

    }
}

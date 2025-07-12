package com.oa7.service;

import com.oa7.pojo.Admin;

import javax.servlet.http.HttpSession;


public interface AdmService {
    //管理员登录
    String login(Admin admin, HttpSession session);
    //管理员注册
    String register(Admin admin);
}

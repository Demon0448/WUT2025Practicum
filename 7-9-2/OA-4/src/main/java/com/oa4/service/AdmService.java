package com.oa4.service;

import com.oa4.pojo.Admin;

import javax.servlet.http.HttpSession;


public interface AdmService {

    Admin login(Admin admin);

    boolean register(Admin admin);
}

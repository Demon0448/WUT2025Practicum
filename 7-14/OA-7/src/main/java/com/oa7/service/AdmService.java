package com.oa7.service;

import com.oa7.pojo.Admin;

import javax.servlet.http.HttpSession;

public interface AdmService {
    String login(Admin admin, HttpSession session);

    String register(Admin admin);
}

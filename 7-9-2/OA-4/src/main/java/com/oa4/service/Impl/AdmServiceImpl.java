package com.oa4.service.Impl;

import com.oa4.service.AdmService;
import com.oa4.dao.AdmDao;
import com.oa4.pojo.Admin;
import com.liuvei.common.SysFun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
public class AdmServiceImpl implements AdmService {

    @Autowired
    private AdmDao admDao;

    @Override
    public Admin login(Admin admin) {

        //边界检测
        if (admin == null || admin.getName()== null || admin.getPwd() == null) {
            return null;
        }

        // 调用数据访问层方法进行查询
        Admin foundAdmin = admDao.selectByName(admin);

        //将密码进行MD5加密并比较
        if (foundAdmin != null && foundAdmin.getPwd().equals(SysFun.md5( admin.getPwd()))) {
            return foundAdmin; // 登录成功，返回管理员信息
        }

        // 登录失败，返回null
        return null;

    }

    @Override
    public boolean register(Admin admin) {

        //1.防止重名
        if (admDao.selectByName(admin) != null) {
            return false;
        }

        //2.密码进行MD5加密
        admin.setPwd(SysFun.md5(admin.getPwd()));

        //3.调用数据访问层方法进行注册
        return admDao.insertAdm(admin) > 0;

    }
}

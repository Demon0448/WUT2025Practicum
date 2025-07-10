package com.oa2.controller;

import com.oa2.dao.EmpDao;
import com.oa2.dao.SignDao;
import com.oa2.pojo.Emp;
import com.oa2.pojo.Sign;
import com.oa2.service.SignService;
import com.oa2.util.DU;
import com.oa2.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/sign")
public class SignController {

    @Autowired
    private SignService service;

    //当前签到查询
    @RequestMapping("/empSignList")
    public RESP empSignList(HttpSession session) {
        System.out.println("1当前签到查询");
        return service.empSignList(session);
    }


    //pagehelper实现用户签到分页查询
    @RequestMapping("/selectByPage")
    public RESP selectByPage(@RequestParam(name="currentPage") String currentPage,
                             @RequestParam(name="pageSize") String pageSize,
                             HttpSession session){
        System.out.println("2实现用户签到分页查询");
        return service.selectByPagehelper(Integer.parseInt(currentPage),Integer.parseInt(pageSize),session);

    }

    //员工签到
    @RequestMapping("/updateState")
    @ResponseBody
    public RESP updateState(Sign sign , HttpSession session,@RequestParam("cor") String cor) {
        System.out.println(sign.getNumber());
        System.out.println(sign.getSignDate());
        System.out.println(cor);
        return service.updateState(sign , session,cor);
    }
}

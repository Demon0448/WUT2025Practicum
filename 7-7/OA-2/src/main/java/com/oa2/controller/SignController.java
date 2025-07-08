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
    private SignService signService;

    @RequestMapping("/empSignList")
    @ResponseBody
    public RESP empSignList(HttpSession session) {
        //调用service层的打卡方法
        RESP result =  signService.empSignList(session);
        return result;
    }


    @RequestMapping("/updateState")
    @ResponseBody
    public RESP updateState(Sign sign,HttpSession session,@RequestParam("cor") String cor) {
        //检查打卡状态是否为0
        //调用service层的更新打卡状态方法
        System.out.println("Received sign: " + sign);
        return signService.updateState(sign, session, cor);
    }


}

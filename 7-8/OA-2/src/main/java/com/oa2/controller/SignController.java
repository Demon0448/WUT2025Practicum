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
    public RESP empSignList(HttpSession session){

        return signService.empSignList(session);

    }

    @RequestMapping("/updateState")
    @ResponseBody
    public RESP updateState(Sign sign,HttpSession session,@RequestParam("cor") String cor){
        return signService.updateState(sign, session, cor);
    }


    @RequestMapping("/selectByPage")
    public RESP selectByPage(@RequestParam ("currentPage") String currentPage, @RequestParam ("pageSize")String pageSize, HttpSession session){
        System.out.println("currentPage:"+currentPage+"  pageSize:"+pageSize);
        return signService.selectByPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize), session);
    }

}

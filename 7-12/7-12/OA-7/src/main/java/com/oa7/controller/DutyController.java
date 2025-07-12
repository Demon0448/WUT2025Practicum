package com.oa7.controller;

import com.oa7.service.DutyService;
import com.oa7.pojo.Duty;
import com.oa7.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/duty")
public class DutyController {

    @Autowired
    private DutyService dutyService;

    //查找每个职务的人数和名称
    @RequestMapping("/selectAllDutyAndNum")
    @ResponseBody
    public RESP selectAllDutyAndNum(@RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return dutyService.selectAllDutyAndNum(Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }

    //更新职务名称
    @RequestMapping("/updateDutyNameById")
    @ResponseBody
    public RESP updateDutyNameById(Duty duty , @RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return dutyService.updateDutyNameById(duty , Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }

    //添加职务
    @RequestMapping("/addDuty")
    @ResponseBody
    public RESP addDuty(Duty duty , @RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return dutyService.addDuty(duty , Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }
}

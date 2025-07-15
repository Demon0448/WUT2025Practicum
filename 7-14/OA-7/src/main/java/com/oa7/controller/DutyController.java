package com.oa7.controller;

import com.oa7.pojo.Duty;
import com.oa7.service.DutyService;
import com.oa7.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class DutyController {

    @Autowired
    private DutyService dutyService;

    //查找每个职务的人数和名称
    @RequestMapping("/duties")
    @ResponseBody
    public RESP selectAllDutyAndNum(@RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return dutyService.selectAllDutyAndNum(Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }

    //路径 /duties/{duty_id} PUT duty_name
    //根据职务id更改职务名称
    @RequestMapping("/duties/{duty_id}")
    @ResponseBody
    public RESP updateDutyNameById(@PathVariable("duty_id") String duty_id , @RequestBody String duty_name) {
        return dutyService.updateDutyNameById(duty_id, duty_name);
    }

    //路径 /duties/add POST duty_name
    @RequestMapping("/duties/add")
    @ResponseBody
    public RESP addDuty(@RequestBody String duty_name) {
        return dutyService.addDuty(duty_name);
    }




    //更新职务名称
    @RequestMapping("/updateDutyNameById")
    @ResponseBody
    public RESP updateDutyNameById(Duty duty , @RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return dutyService.updateDutyNameById(duty , Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }

    //添加职务 弃用
    @RequestMapping("/addDuty")
    @ResponseBody
    public RESP addDuty(Duty duty , @RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return null;
    }
}

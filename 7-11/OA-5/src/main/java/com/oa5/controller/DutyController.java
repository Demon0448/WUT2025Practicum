package com.oa5.controller;


import com.oa5.pojo.Duty;
import com.oa5.service.DutyService;
import com.oa5.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/duty")
public class DutyController {
    @Autowired
    private DutyService dutyService;
    //selectAllDutyAndNum POST 参数 currentPage pageSize
    @RequestMapping("/selectAllDutyAndNum")
    @ResponseBody
    public RESP selectAllDutyAndNum(Integer currentPage, Integer pageSize) {
        return dutyService.selectAllDutyAndNum(currentPage, pageSize);
    }
    //updateDutyNameById POST 参数 duty_id duty_name currentPage pageSize
    @RequestMapping("/updateDutyNameById")
    @ResponseBody
    public RESP updateDutyNameById(Duty duty, Integer currentPage, Integer pageSize) {
        return dutyService.updateDutyNameById(duty, currentPage, pageSize);
    }

    //addDuty POST 参数 duty_name currentPage pageSize
    @RequestMapping("/addDuty")
    @ResponseBody
    public RESP addDuty(Duty duty, Integer currentPage, Integer pageSize) {
        return dutyService.addDuty(duty, currentPage, pageSize);
    }
}

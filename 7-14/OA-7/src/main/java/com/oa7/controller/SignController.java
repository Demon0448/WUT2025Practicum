package com.oa7.controller;


import com.oa7.pojo.Sign;
import com.oa7.service.SignService;
import com.oa7.util.RESP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 员工端 - 签到管理控制器
 */
@RestController
@RequestMapping("/attendance")
@CrossOrigin
@Slf4j
public class SignController {

    @Autowired
    private SignService signService;

    //路径 today/signed GET 无参数

    //TODO ES 实现
    @GetMapping("/today/signed")
    public RESP getTodaySigned() {
        return signService.getTodaySigned();
    }


    //TODO Mysql实现
    //路径 /daily-statistics?currentPage=1&pageSize=8
    @GetMapping("/daily-statistics")
    public RESP getDailyStatistics(@RequestParam(name = "currentPage") String currentPage ,
                                   @RequestParam(name = "pageSize") String pageSize) {
        return signService.selectDaySignList(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
    }

    //TODO ES 实现
    //路径 /statistics/chart GET
    @GetMapping("/statistics/chart")
    public RESP getStatisticsChart(@RequestParam(name = "currentPage") String currentPage ,
                                   @RequestParam(name = "pageSize") String pageSize) {
        return signService.getStatisticsChart(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
    }


    //TODO ElasticSearch
    //路径 daily-details GET String date
    @GetMapping("/daily-details")
    public RESP getDailyDetails(@RequestParam(name = "date") String date) {
        return signService.getDailyDetails(date);
    }

    //路径 /unsigned GET currentPage=1&pageSize=8
    @GetMapping("/unsigned")
    public RESP getUnsigned(@RequestParam(name = "currentPage") String currentPage ,
                            @RequestParam(name = "pageSize") String pageSize) {
        return signService.getUnsigned(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
    }

    //TODO Mysql实现
    //路径 /signed GET currentPage=1&pageSize=8
    @GetMapping("/signed")
    public RESP getSigned(@RequestParam(name = "currentPage") String currentPage ,
                          @RequestParam(name = "pageSize") String pageSize) {
        return signService.getSigned(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
    }

    //TODO Mysql ES 实现同步，手动，可延伸MQ
    //路径 /{id}/reject PUT
    @PutMapping("/{id}/reject")
    public RESP rejectSign(@PathVariable("id") Integer id) {
        return signService.rejectSign(id);
    }

    //TODO Mysql ES 实现同步，手动，可延伸MQ
    //路径 /{id}/approve PUT
    @PutMapping("/{id}/approve")
    public RESP approveSign(@PathVariable("id") Integer id) {
        return signService.approveSign(id);
    }



    /**
     * 获取当前员工的签到列表
     */
    @GetMapping("/my-records")
    public RESP getMyAttendanceRecords(HttpSession session) {
        System.out.println("1获取当前员工的签到列表");
        try {
            return signService.empSignList(session);
        } catch (Exception e) {
            e.printStackTrace();
            return RESP.error("获取签到记录失败：" + e.getMessage());
        }
    }

    /**
     * 员工签到
     */
    @PostMapping("/check-in")
    public RESP checkIn(@RequestBody Sign sign, HttpSession session, @RequestParam("coordinates") String coordinates) {
        System.out.println("2员工签到");
        try {
            return  signService.updateState(sign, session, coordinates);
        } catch (Exception e) {
            e.printStackTrace();
            return RESP.error("签到失败：" + e.getMessage());
        }
    }


    /**
     * 分页查询当前员工的签到记录
     */
    @GetMapping("/my-records/page")
    public RESP getMyAttendanceRecordsByPage(
            @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            HttpSession session) {
        System.out.println("3分页查询当前员工的签到记录");
        try {
            return signService.selectByPagehelper(currentPage, pageSize, session);
        } catch (Exception e) {
            e.printStackTrace();
            return RESP.error("获取签到记录失败：" + e.getMessage());
        }
    }


    //TODO mysql->ES
    @GetMapping("/load")
    public RESP loadSignData() {
        return signService.loadSignData();
    }



    //TODO mysql实现
    @RequestMapping("/searchByEmployeeNumberAndState")
    @ResponseBody
    public RESP searchByEmployeeNumberAndState(@RequestParam String employeeNumber,
                                               @RequestParam String state,
                                               @RequestParam(defaultValue = "1") Integer currentPage,
                                               @RequestParam(defaultValue = "8") Integer pageSize) {
        log.info("调用路径：searchByEmployeeNumber");
        return signService.searchByEmployeeNumberAndState(employeeNumber,state ,currentPage, pageSize);
    }



}

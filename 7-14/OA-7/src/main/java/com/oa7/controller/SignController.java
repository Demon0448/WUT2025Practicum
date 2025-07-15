package com.oa7.controller;


import com.oa7.pojo.Sign;
import com.oa7.service.SignService;
import com.oa7.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 员工端 - 签到管理控制器
 */
@RestController
@RequestMapping("/attendance")
@CrossOrigin
public class SignController {

    @Autowired
    private SignService signService;

    //路径 today/signed GET 无参数
    @GetMapping("/today/signed")
    public RESP getTodaySigned() {
        return signService.getTodaySigned();
    }

    //路径 /daily-statistics?currentPage=1&pageSize=8
    @GetMapping("/daily-statistics")
    public RESP getDailyStatistics(@RequestParam(name = "currentPage") String currentPage ,
                                   @RequestParam(name = "pageSize") String pageSize) {
        return signService.selectDaySignList(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
    }

    //路径 /statistics/chart GET
    @GetMapping("/statistics/chart")
    public RESP getStatisticsChart() {
        return signService.getStatisticsChart();
    }

    //路径 daily-details GET String date
    @GetMapping("/daily-details")
    public RESP getDailyDetails(@RequestParam(name = "date") String date) {
        return signService.getDailyDetails(date);
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


}

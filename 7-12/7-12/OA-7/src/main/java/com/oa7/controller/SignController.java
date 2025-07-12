package com.oa7.controller;

import com.oa7.service.SignService;
import com.oa7.pojo.Sign;
import com.oa7.util.DU;
import com.oa7.util.RESP;
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

    //查询某个员工所有考勤信息
    @RequestMapping("/selectByPage")
    @ResponseBody
    public RESP selectByPage(@RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize , HttpSession session) {
        return signService.selectByPage(Integer.parseInt(currentPage) , Integer.parseInt(pageSize) , session);
    }

    //查询所有的考勤信息
    @RequestMapping("/selectAllByPage")
    @ResponseBody
    public RESP selectAllByPage(@RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return signService.selectAllByPage(Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }

    //更新某条考勤的状态
    @RequestMapping("/updateState")
    @ResponseBody
    public RESP updateState(Sign sign , HttpSession session) {
        return signService.updateState(sign , session);
    }

    //补签
    @RequestMapping("/updateStateYes")
    @ResponseBody
    public RESP updateStateYes(Sign sign , @RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return signService.updateStateYes(sign , Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }

    //驳回签到
    @RequestMapping("/updateStateNo")
    @ResponseBody
    public RESP updateStateNo(Sign sign , @RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return signService.updateStateNo(sign , Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }

    //检查考勤的状态
    public List<Sign> check(List<Sign> list) {
        System.out.println("开始查询考情状态");
        List<Sign> list1 = new ArrayList<>();
        for (Sign sign : list) {
            if (sign.getState().equals("未签到")) {
                if (sign.getSignDate().contains("08:30:00:00")) {
                    if (DU.IsAMMillis(System.currentTimeMillis())) {
                        sign.setTag(1);
                    } else {
                        sign.setTag(0);
                    }
                } else if (sign.getSignDate().contains("14:30:00:00")) {
                    if (DU.IsPMMillis(System.currentTimeMillis())) {
                        sign.setTag(1);
                    } else {
                        sign.setTag(0);
                    }
                }
            } else if (sign.getState().equals("已签到")) {
                sign.setTag(0);
            }
            list1.add(sign);
        }
        return list1;
    }

    @RequestMapping("/empSignList")
    @ResponseBody
    public RESP empSignList(HttpSession session) {
        return signService.empSignList(session);
    }

    //查询已签到的考勤信息
    @RequestMapping("/selectYesByPage")
    @ResponseBody
    public RESP selectYesByPage(@RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return signService.selectYesByPage(Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }

    //查询未签到的考勤信息
    @RequestMapping("/selectNoByPage")
    @ResponseBody
    public RESP selectNoByPage(@RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return signService.selectNoByPage(Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }

    //查询今日已签到的考勤信息
    @RequestMapping("/selectToDayYesByPage")
    @ResponseBody
    public RESP selectToDayYesByPage(@RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return signService.selectToDayYesByPage(Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }

    //查询今日未签到的考勤信息
    @RequestMapping("/selectToDayNoByPage")
    @ResponseBody
    public RESP selectToDayNoByPage(@RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return signService.selectToDayNoByPage(Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }

    //查询并统计每日考勤信息
    @RequestMapping("/selectDaySignList")
    @ResponseBody
    public RESP selectDaySignList(@RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return signService.selectDaySignList(Integer.parseInt(currentPage) , Integer.parseInt(pageSize));

    }

    //获取近五日签到统计图数据


    @RequestMapping("/selectImgSignList")
    @ResponseBody
    public RESP selectImgSignList(){

        return signService.selectImgSignList();
    }
}

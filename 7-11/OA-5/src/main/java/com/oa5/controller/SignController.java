package com.oa5.controller;


import com.oa5.pojo.Sign;
import com.oa5.service.SignService;
import com.oa5.util.RESP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign")
@Slf4j
public class SignController {

    @Autowired
    private SignService signService;

    //路径 selectDaySignList POST 参数 currentPage pageSize
    @RequestMapping("/selectDaySignList")
    public RESP selectDaySignList(Integer currentPage, Integer pageSize) {
        return signService.selectDaySignList(currentPage, pageSize);

    }
    //路径 selectYesByPage  POST 参数 currentPage pageSize
    @RequestMapping("/selectYesByPage")
    public RESP selectYesByPage(Integer currentPage, Integer pageSize) {
        log.info("调用路径：selectYesByPage");
        return signService.selectYesByPage(currentPage,pageSize);
    }

    //路径 selectToDayYesByPage POST 参数 currentPage pageSize
    @RequestMapping("/selectToDayYesByPage")
    public RESP selectToDayYesByPage(Integer currentPage, Integer pageSize) {

        log.info("调用路径：selectToDayYesByPage");
        return signService.selectToDayYesByPage(currentPage,pageSize);
    }

    //路径 selectToDayNoByPage POST 参数 currentPage pageSize
    @RequestMapping("/selectToDayNoByPage")
    public RESP selectToDayNoByPage(Integer currentPage, Integer pageSize) {

        log.info("调用路径：selectToDayNoByPage");
        return signService.selectToDayNoByPage(currentPage,pageSize);
    }



    //路径 selectNoByPage  POST 参数 currentPage pageSize
    @RequestMapping("/selectNoByPage")
    public RESP selectNoByPage(Integer currentPage, Integer pageSize) {
        log.info ("调用路径：selectNoByPage");
        return signService.selectNoByPage(currentPage,pageSize);

    }
    //路径 selectImgSignList POST TODO 暂无参数 需要修改
    //    selectImgSignList
    @RequestMapping("/selectImgSignList")
    public RESP selectImgSignList(@RequestParam(defaultValue = "1") Integer currentPage, 
                              @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("调用路径：selectImgSignList {},{}", currentPage, pageSize);
        return signService.selectImgSignList(currentPage, pageSize);
    }

    //路径 updateStateYes POST 参数 number currentPage pageSize state signDate
    @RequestMapping("/updateStateYes")
    @ResponseBody
    public RESP updateStateYes(Sign sign, Integer currentPage, Integer pageSize) {
        log.info("调用路径：updateStateYes");
        return signService.updateStateYes(sign, currentPage, pageSize);
    }


    //路径 updateStateNo POST  参数 number currentPage pageSize state signDate
    @RequestMapping("/updateStateNo")
    @ResponseBody
    public RESP updateStateNo(Sign sign, Integer currentPage, Integer pageSize) {
        log.info("调用路径：updateStateNo");
        return signService.updateStateNo(sign, currentPage, pageSize);
    }

}

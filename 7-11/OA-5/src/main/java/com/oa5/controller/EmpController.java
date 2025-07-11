package com.oa5.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.oa5.service.EmpService;
import com.oa5.dao.EmpDao;
import com.oa5.pojo.Emp;
import com.oa5.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private EmpDao empDao;
    @Autowired
    private EmpService empService;


    @RequestMapping("/selectByPage")
    @ResponseBody
    public RESP selectByPage(@RequestParam(name = "currentPage") String currentPage, @RequestParam(name = "pageSize") String pageSize) {
        RESP resp = empService.selectByPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
        return resp;
    }


    //获取部门信息-下拉列表
    @RequestMapping("/getDeptData")
    @ResponseBody
    public RESP getDeptData() {
        //  return empService.getDeptData();
        return empService.getDeptDataFinDall();
    }

    //获取职务信息-下拉列表
    @RequestMapping("/getDutyData")
    @ResponseBody
    public RESP getDutyData() {
        return empService.getDutyData();
    }

    // 添加用户
    @RequestMapping("/addEmp")
    @ResponseBody
    public RESP addEmp(Emp emp, @RequestParam(name = "currentPage") String currentPage, @RequestParam(name = "pageSize") String pageSize) {
        return empService.addEmp(emp, Integer.parseInt(currentPage), Integer.parseInt(pageSize));
    }

    //更新用户信息
    @RequestMapping("/updateEmp")
    @ResponseBody
    public RESP updateEmp(Emp emp, @RequestParam(name = "currentPage") String currentPage, @RequestParam(name = "pageSize") String pageSize) {
        return empService.updateEmp(emp, Integer.parseInt(currentPage), Integer.parseInt(pageSize));
    }

    //删除用户
    @RequestMapping("/delete")
    @ResponseBody
    public RESP deleteEmp(Emp emp, @RequestParam(name = "currentPage") String currentPage, @RequestParam(name = "pageSize") String pageSize) {
        return empService.deleteEmp(emp, Integer.parseInt(currentPage), Integer.parseInt(pageSize));
    }

    //更新用户的人事信息
    @RequestMapping("/updateDD")
    @ResponseBody
    public RESP updateDD(Emp emp, @RequestParam(name = "currentPage") String currentPage, @RequestParam(name = "pageSize") String pageSize) {
        return empService.updateDD(emp, Integer.parseInt(currentPage), Integer.parseInt(pageSize));
    }

}

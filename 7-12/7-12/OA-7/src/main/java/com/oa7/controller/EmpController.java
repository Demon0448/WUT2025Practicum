package com.oa7.controller;

import com.oa7.pojo.Emp;
import com.oa7.service.EmpService;
import com.oa7.dao.EmpDao;
import com.oa7.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private EmpDao empDao;
    @Autowired
    private EmpService empService;

    //根据page查询用户信息
    @RequestMapping("/selectByPage")
    @ResponseBody
    public RESP selectByPage(@RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return empService.selectByPage(Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
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

    //更新用户的人事信息
    @RequestMapping("/updateDD")
    @ResponseBody
    public RESP updateDD(Emp emp , @RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return empService.updateDD(emp , Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }

    //删除用户
    @RequestMapping("/delete")
    @ResponseBody
    public RESP deleteEmp(Emp emp , @RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return empService.deleteEmp(emp , Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }

    // 添加用户
    @RequestMapping("/addEmp")
    @ResponseBody
    public RESP addEmp(Emp emp , @RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return empService.addEmp(emp , Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }


    //更新用户信息
    @RequestMapping("/updateEmp")
    @ResponseBody
    public RESP updateEmp(Emp emp , @RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return empService.updateEmp(emp , Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }
}

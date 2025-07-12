package com.oa7.controller;

import com.oa7.service.DeptService;
import com.oa7.pojo.Department;
import com.oa7.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;
    //查询所有部门的人数以及部门信息
    @RequestMapping("/selectAllDeptAndNum")
    @ResponseBody
    public RESP selectAllDeptAndNum(@RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return deptService.selectAllDeptAndNum(Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }

    //更新部门名称
    @RequestMapping("/updateDeptNameById")
    @ResponseBody
    public RESP updateDeptNameById(Department department , @RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return deptService.updateDeptNameById(department , Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }

    //添加部门
    @RequestMapping("/addDept")
    @ResponseBody
    public RESP addDept(Department department , @RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return deptService.addDept(department , Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }

}

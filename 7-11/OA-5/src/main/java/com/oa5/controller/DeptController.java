package com.oa5.controller;

import com.oa5.pojo.Department;
import com.oa5.service.DeptService;
import com.oa5.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    //路径 selectAllDeptAndNum POST 参数 currentPage pageSize
    @RequestMapping("/selectAllDeptAndNum")
    public RESP selectAllDeptAndNum(@RequestParam(name = "currentPage")Integer currentPage, @RequestParam(name = "pageSize") Integer pageSize) {
        return deptService.selectAllDeptAndNum(currentPage, pageSize);
    }
    //路径 addDept POST 参数 dept_name currentPage pageSize
    @RequestMapping("/addDept")
    public RESP addDept(@RequestParam(name = "dept_name") String dept_name, @RequestParam(name = "currentPage") Integer currentPage, @RequestParam(name = "pageSize") Integer pageSize) {
        return deptService.addDept(dept_name, currentPage, pageSize);
    }
    //路径 updateDeptNameById POST 参数 dept_id dept_name currentPage pageSize
    @RequestMapping("/updateDeptNameById")
    public RESP updateDeptNameById(@RequestParam(name = "dept_id") Integer dept_id, @RequestParam(name = "dept_name") String dept_name, @RequestParam(name = "currentPage") Integer currentPage, @RequestParam(name = "pageSize") Integer pageSize) {
        return deptService.updateDeptNameById(dept_id, dept_name, currentPage, pageSize);
    }
}

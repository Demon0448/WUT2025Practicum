package com.oa7.controller;


import com.oa7.service.DeptService;
import com.oa7.util.RESP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class DeptController {
    @Autowired
    private DeptService deptService;
    //路径 departments GET currentPage pageSize
    //根据page查询部门信息
    @GetMapping("/departments")
    @ResponseBody
    public RESP selectAllDeptAndNum(@RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return deptService.selectAllDeptAndNum(Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }

    //路径 departments POST
    @PostMapping("/departments/add")
    @ResponseBody
    public RESP insertDept(@RequestBody String deptName,@RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return deptService.insertDept(deptName, Integer.parseInt(currentPage), Integer.parseInt(pageSize));
    }

    //路径 /departments/{dept_id} PUT
    @PutMapping("/departments/{dept_id}")
    @ResponseBody
    public RESP updateDeptNameById(@PathVariable("dept_id") String dept_id, @RequestBody String deptName,@RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        log.info("Updating department ID {} to new name: {}", dept_id, deptName);
        return deptService.updateDeptNameById(dept_id, deptName, Integer.parseInt(currentPage), Integer.parseInt(pageSize));
    }




}


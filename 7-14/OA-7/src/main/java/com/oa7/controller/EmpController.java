package com.oa7.controller;


import com.oa7.pojo.Emp;
import com.oa7.service.EmpService;
import com.oa7.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 员工端 - 员工管理控制器
 */
@RestController
@RequestMapping
@CrossOrigin
public class EmpController {

    @Autowired
    private EmpService empService;

    //根据page查询用户信息
    @GetMapping("/employees")
    @ResponseBody
    public RESP selectByPage(@RequestParam(name = "currentPage") String currentPage , @RequestParam(name = "pageSize") String pageSize) {
        return empService.selectByPage(Integer.parseInt(currentPage) , Integer.parseInt(pageSize));
    }
    //路径 employees/departments GET
    @GetMapping("/employees/departments")
    @ResponseBody
    public RESP selectAllDept() {

        return empService.selectAllDept();
    }


    //路径 /employees/duties GET
    @GetMapping("/employees/duties")
    @ResponseBody
    public RESP selectAllDuty() {
        return empService.selectAllDuty();
    }

    @PostMapping("/employees/add")
    @ResponseBody
    public RESP addEmp(@RequestBody Emp emp){
        return empService.addEmp(emp);
    }


//    const response = await axios.put(`/api/v1/admin/employees/${editFormData.number}`, empData, {
//        params: {
//            currentPage: pagination.currentPage,
//                    pageSize: pagination.pageSize
//        },
//        headers: {
//            'Content-Type': 'application/json'
//        }
//    })

    //路径 /employees/{number} PUT
    @PutMapping("/employees/{number}")
    @ResponseBody
    public RESP updateEmp(@PathVariable("number") String number, @RequestBody Emp emp,Integer currentPage, Integer pageSize) {
        emp.setNumber(Integer.parseInt(number));
        return empService.updateEmp(emp, currentPage, pageSize);
    }

    //路径 /employees/{number} DELETE
    @DeleteMapping("/employees/{number}")
    @ResponseBody
    public RESP deleteEmp(@PathVariable("number") String number) {
        return empService.deleteEmp(Integer.parseInt(number));
    }

}

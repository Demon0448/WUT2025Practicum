package com.oa4.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.oa4.pojo.Emp;
import com.oa4.service.EmpService;
import com.oa4.dao.EmpDao;
import com.oa4.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 员工管理控制器
 */

@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    //分页查询员工列表

    @RequestMapping("/selectByPage")
    public RESP selectByPage(@RequestParam(name="currentPage") int currentPage,
                             @RequestParam(name="pageSize") int pageSize,
                             HttpSession session){

        return empService.selectByPage(currentPage,pageSize,session);
    }


    //添加员工信息
    @RequestMapping("/addEmp")
    @ResponseBody
    public RESP addEmp(Emp emp,@RequestParam(name="currentPage") int currentPage,
                       @RequestParam(name="pageSize") int pageSize, HttpSession session) {
        return empService.addEmp(emp, currentPage, pageSize, session);
    }

    //获取部门列表
    @RequestMapping("/getDeptData")
    @ResponseBody
    public RESP getDeptList(){
        return empService.getDeptList();
    }

    //获取职务列表
    @RequestMapping("/getDutyData")
    @ResponseBody
    public RESP getDutyList(){
        return empService.getDutyList();
    }


    //修改员工信息
    @RequestMapping("/updateEmp")
    @ResponseBody
    public RESP updateEmp(Emp emp,@RequestParam(name="currentPage") int currentPage,
                          @RequestParam(name="pageSize") int pageSize, HttpSession session){
        return empService.updateEmp(emp, currentPage, pageSize, session);
    }

    //删除员工
    @RequestMapping("/delete")
    @ResponseBody
    public RESP deleteEmp(Emp emp,@RequestParam(name="currentPage") int currentPage,
                          @RequestParam(name="pageSize") int pageSize, HttpSession session){
        return empService.deleteEmp(emp, currentPage, pageSize, session);
    }


    //修改员工部门植物
    @RequestMapping("/updateDD")
    @ResponseBody
    public RESP updateDD(Emp emp,@RequestParam(name="currentPage") int currentPage,
                          @RequestParam(name="pageSize") int pageSize, HttpSession session){
        return empService.updateDD(emp, currentPage, pageSize, session);
    }
}

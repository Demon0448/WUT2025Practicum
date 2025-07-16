package com.oa2.controller;

import com.oa2.feign.AdminFeignClient;
import com.oa2.service.EmpService;
import com.oa2.pojo.Emp;
import com.oa2.util.RESP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 员工端 - 员工管理控制器
 */
@RestController
@RequestMapping
@CrossOrigin
@Slf4j
public class EmpController {

    @Autowired
    private EmpService empService;


    @Autowired
    private AdminFeignClient adminFeignClient;

    /**
     * 员工登录
     */
    @PostMapping("/login")
    public String login(@RequestBody Emp emp, HttpSession session, HttpServletRequest request) {

        //打印http完整请求路径
        log.info("请求路径：{}" , request.getRequestURI());

        return empService.emplogin(emp, session);
    }

    /**
     * 获取当前登录员工信息
     */
    @GetMapping("/profile")
    public RESP getProfile(HttpSession session) {
        Emp emp = (Emp) session.getAttribute("emp");
        return RESP.ok(emp);
    }

    /**
     * 更新员工密码
     */
    @PutMapping("/password")
    public String updatePassword(@RequestBody Emp emp, @RequestParam("oldPassword") String oldPassword) {
        return empService.updateEmpPwd(emp, oldPassword);
    }

    /**
     * 更新员工个人信息
     */
    @PutMapping("/profile")
    public RESP updateProfile(@RequestBody Emp emp, HttpSession session) {
        return empService.updateInfo(emp, session);
    }

    /**
     * 员工退出登录
     */
    @PostMapping("/logout")
    public RESP logout(HttpSession session) {
        session.removeAttribute("emp");
        return RESP.ok("退出登录成功");
    }
}

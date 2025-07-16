package com.oa7.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuvei.common.SysFun;

import com.oa7.dao.DeptDao;
import com.oa7.dao.DutyDao;
import com.oa7.dao.EmpDao;
import com.oa7.pojo.Department;
import com.oa7.pojo.Duty;
import com.oa7.pojo.Emp;
import com.oa7.service.EmpService;
import com.oa7.util.RESP;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;


@Service
@Slf4j
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpDao empDao;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private DutyDao dutyDao;

    @Autowired
    private RedisTemplate redisTemplate;

    //员工登录
    @Override
    public String emplogin(Emp emp , HttpSession session) {
        Emp emp1 = empDao.selectByNumber(emp);
        if (emp1 != null) {
            if (emp1.getPwd().equals(SysFun.md5(emp.getPwd()))) {
                session.setAttribute("emp" , emp1);
                return "true";
            }
        }
        return "false";
    }

    //更新密码
    @Override
    public String updateEmpPwd(Emp emp , String oldpwd) {
        Emp emp1 = empDao.selectByNumber(emp);
        if (emp1.getPwd().equals(SysFun.md5(oldpwd))) {
            emp.setPwd(SysFun.md5(emp.getPwd()));
            int r = empDao.updateEmpPwd(emp);
            if (r > 0) {
                return "true";
            }
        }
        return "false";
    }

    @Override
    public RESP updateInfo(Emp emp, HttpSession session) {
        int i=empDao.updateEmp(emp);
        if(i>0){
            Emp emp1 = empDao.selectByNumber(emp);
            session.setAttribute("emp",emp1);
            return RESP.ok(emp1);
        }
        return null;
    }

    @Override
    public RESP selectByPage(int currentPage, int pageSize) {

        //TODO  是否分页但是共用接口 约定-1 为 不分页查询
        if (currentPage < 0 || pageSize < 0) {
            int count = empDao.countEmp();
            return RESP.ok(null,-1, count);
        }

        log.info("currentPage:"+currentPage+"  pageSize:"+pageSize);
        PageHelper.startPage(currentPage, pageSize,true);
        List<Emp> list = empDao.selectByPageHelper();
        PageInfo<Emp> data = new PageInfo<>(list);
        return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
    }

    @Override
    public RESP selectTodaySigned(int currentPage, int pageSize) {
        return null;
    }

    @Override
    public RESP selectAllDept() {
        //redis 改造
        String key = "dept";
        List<Department> ans = (List<Department>) redisTemplate.opsForValue().get(key);
        if (ans == null) {
            ans = deptDao.selectAll();
            if (ans != null) {
                // 将查询结果存入 Redis
                redisTemplate.opsForValue().set(key, ans);
            } else {
                log.error("查询部门信息失败");
                return RESP.error("查询部门信息失败");
            }
        }
        log.info("查询部门信息成功，数量：" + ans.size());



        return RESP.ok(ans);
    }

    @Override
    public RESP selectAllDuty() {
        //redis 改造
        String key = "duty";
        List<Duty> ans = (List<Duty>) redisTemplate.opsForValue().get(key);
        if (ans == null) {
            ans = dutyDao.selectAll();
            if (ans != null) {
                // 将查询结果存入 Redis
                redisTemplate.opsForValue().set(key, ans);
            } else {
                log.error("查询职务信息失败");
                return RESP.error("查询职务信息失败");
            }
        }

        return RESP.ok(ans);

    }

    @Override
    public RESP addEmp(Emp emp) {
        log.info("添加员工信息：" + emp);
        Emp emp1 = empDao.selectByName(emp);
        if(emp1!=null){
            return null;
        }else {
            log.info("添加员工信息：" + emp);
            int r = empDao.addEmp(emp);
            if (r > 0) {
                return RESP.ok(emp);
            }
        }
        return null;
    }

    @Override
    public RESP updateEmp(Emp emp, Integer currentPage, Integer pageSize) {
        log.info("修改员工信息：" + emp);
        Emp emp1 = empDao.selectByNumber(emp);
        if(emp1!=null){
            int r = empDao.updateEmp(emp);
            if (r > 0) {
                return selectByPage(currentPage, pageSize);
            }
        }
        return null;
    }

    @Override
    public RESP deleteEmp(int number) {
        log.info("删除员工编号：" + number);
        Emp emp =empDao.selectByEmpNumber(number);
        if(empDao.deleteEmpByNumber(emp) > 0){
            return RESP.ok("删除成功");
        }
        return null;
    }



    private void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}

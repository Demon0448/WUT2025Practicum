package com.oa4.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa4.pojo.Department;
import com.oa4.service.EmpService;
import com.oa4.dao.EmpDao;
import com.oa4.pojo.Emp;
import com.oa4.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;


@Service
public class EmpServic1eImpl implements EmpService {
    @Autowired
    private EmpDao empDao;


    @Override
    public RESP selectByPage(int currentPage, int pageSize, HttpSession session) {
        System.out.println("使用PageHelper实现分页");
        //1使用Session获取当前用户信息

        System.out.println(currentPage + " " + pageSize);

        //2 分页的实现
        PageHelper.startPage(currentPage, pageSize, true);
        List<Emp> list = empDao.selectByPageHelper();
        PageInfo<Emp> data = new PageInfo<Emp>(list);
        System.out.println(data.getList());

        //3 返回结果
        return RESP.ok(data.getList(), data.getPageNum(), data.getTotal());

    }

    @Override
    public RESP addEmp(Emp emp, int currentPage, int pageSize, HttpSession session) {

        //先添加员工
        //默认员工存在重名，无需边界检查
        //设置默认密码123即可,但是dao层未处理
        //TODO 部门映射

        int i = empDao.addEmp(emp);
        if(i>0){
            return selectByPage(currentPage,pageSize,session);
        }
        return null;
    }

    @Override
    public RESP getDeptList() {
        return RESP.ok(empDao.getDeptData());
    }

    @Override
    public RESP getDutyList() {
        return RESP.ok(empDao.getDutyData());
    }

    @Override
    public RESP updateEmp(Emp emp, int currentPage, int pageSize, HttpSession session) {

        System.out.println("更新员工信息开始");
        //边界检查需要吗
        //查名字
        System.out.println(emp);

        //TODO
        Emp empAns = empDao.selectByNumber(emp);
        if(empAns == null){
            return null;
        }

        empAns.setDept_id(emp.getDept_id());
        empAns.setDuty_id(emp.getDuty_id());



        int i=empDao.updateEmp(empAns);
        if(i>0){
            return selectByPage(currentPage,pageSize,session);

        }
        return null;
    }

    @Override
    public RESP deleteEmp(Emp emp, int currentPage, int pageSize, HttpSession session) {

        //边界检查需要吗
        int i=empDao.deleteEmp(emp);
        if(i>0){
            return selectByPage(currentPage,pageSize,session);
        }
        return null;
    }
}

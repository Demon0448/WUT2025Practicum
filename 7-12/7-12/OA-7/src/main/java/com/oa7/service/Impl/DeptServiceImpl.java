package com.oa7.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa7.pojo.Department;
import com.oa7.pojo.Emp;
import com.oa7.service.DeptService;
import com.oa7.dao.DeptDao;
import com.oa7.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;
//每个部门人数
    @Override
    public RESP selectAllDeptAndNum(int current , int size) {
        System.out.println("查看部分实现分页....");
        PageHelper.startPage(current, size, true);
        List<Department> list = deptDao.selectByPageHelper();
        PageInfo<Department> data = new PageInfo<>(list);
        return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
    }



//更新部门名称
    @Override
    public RESP updateDeptNameById(Department department , int current , int size) {
        Department department1 = deptDao.selectByName(department);
        if (department1 == null) {
            int r = deptDao.updateDeptNameById(department);
            if (r > 0) {
                System.out.println("查看部分实现分页....");
                PageHelper.startPage(current, size, true);
                List<Department> list = deptDao.selectByPageHelper();
                PageInfo<Department> data = new PageInfo<>(list);
                return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
            }
        }
        return null;
    }


//添加部门
    @Override
    public RESP addDept(Department department , int current , int size) {
        Department department1 = deptDao.selectByName(department);
        if (department1 == null) {
            int r = deptDao.addDept(department);
            if (r > 0) {
                System.out.println("查看部分实现分页....");
                PageHelper.startPage(current, size, true);
                List<Department> list = deptDao.selectByPageHelper();
                PageInfo<Department> data = new PageInfo<>(list);
                return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
            }
            return null;
        }
        return null;
    }
}

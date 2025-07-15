package com.oa7.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa7.dao.DeptDao;
import com.oa7.pojo.Department;
import com.oa7.service.DeptService;
import com.oa7.util.RESP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RESP selectAllDeptAndNum(int currentPage, int pageSize) {

        //TODO  是否分页但是共用接口 约定-1 为 不分页查询
        if (currentPage < 0 || pageSize < 0) {
            List<Department> list = deptDao.selectAll();
            int count = deptDao.countDept();
            return RESP.ok(list, 1, count);
        }

        PageHelper.startPage(currentPage, pageSize, true);
        List<Department> list = deptDao.selectByPageHelper();
        PageInfo<Department> data = new PageInfo<>(list);
        return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
    }

    @Override
    public RESP insertDept(String deptName, int currentPage, int pageSize) {
        Department department = new Department();

        //TODO 懒得改前端了，后端搞吧
        JSONObject jsonObject = JSONObject.parseObject(deptName);
        deptName = jsonObject.getString("dept_name");


        department.setDept_name(deptName);
        log.info("添加部门: {}", department.getDept_name());
        Department department1 = deptDao.selectByName(department);

        if (department1 != null) {
            log.info("部门已存在");
            return null;
        }
        int result = deptDao.addDept(department);
        if (result == 0) {
            log.info("添加部门失败");
            return null;
        }

        cleanCache("dept");
        return selectAllDeptAndNum(currentPage, pageSize);
    }

    @Override
    public RESP updateDeptNameById(String dept_id, String deptName, int currentPage, int pageSize) {
        //TODO 懒得改前端了，后端搞吧
        JSONObject jsonObject = JSONObject.parseObject(deptName);
        deptName = jsonObject.getString("dept_name");

        log.info("更新部门ID {} 的名称为: {}", dept_id, deptName);
        Department department = new Department();
        department.setDept_id(Integer.parseInt(dept_id));
        department.setDept_name(deptName);
        int res = deptDao.updateDeptNameById(department);
        if (res == 1) {
            log.info("更新部门ID {} 的名称成功", dept_id);
            cleanCache("dept");
            return selectAllDeptAndNum(currentPage, pageSize);
        }
        return null;
    }




    private void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}

package com.oa5.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa5.dao.DeptDao;
import com.oa5.pojo.Department;
import com.oa5.pojo.Emp;
import com.oa5.service.DeptService;
import com.oa5.util.RESP;
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

        //key: dept_and_num redis
        String key = "dept_and_num:page:" + currentPage + ":size:" + pageSize;
        // 1. 先尝试从 Redis 获取数据
        PageInfo<Department>data=(PageInfo<Department>) redisTemplate.opsForValue().get(key);
        if (data == null) {
            log.info("Redis cache miss for key: {}", key);
            // 2. 缓存未命中，执行数据库查询
            PageHelper.startPage(currentPage, pageSize, true);
            List<Department> list = deptDao.selectAllDeptAndNum();
            data = new PageInfo<>(list);
            // 3. 将结果写入 Redis，并设置过期时间（例如 5 分钟）
            redisTemplate.opsForValue().set(key, data);

        }
        // 4. 返回结果
        return RESP.ok( data.getList(), data.getPageNum(), (int)data.getTotal());

    }

    @Override
    public RESP addDept(String deptName, Integer currentPage, Integer pageSize) {
        // 检查部门名称是否已存在
        Department tmp=new Department();
        tmp.setDept_name(deptName);
        Department existingDept = deptDao.selectByName(tmp);
        if (existingDept != null) {
            log.info("Department already exists: {}", deptName);
            return null;
        }
        // 添加新部门
        if(deptDao.addDept(tmp)==1){
            log.info("Added new department: {}", deptName);
            // 清除缓存
            cleanCache("dept_and_num:*");
            cleanCache("Dept");
            return selectAllDeptAndNum(currentPage, pageSize);
        }
        return null;
    }

    @Override
    public RESP updateDeptNameById(Integer deptId, String deptName, Integer currentPage, Integer pageSize) {
        //1. 检查修改后部门名称是否存在
        Department tmp=new Department();
        tmp.setDept_name(deptName);
        Department existingDept = deptDao.selectByName(tmp);
        if (existingDept != null && existingDept.getDept_id()!=(deptId)) {
            log.info("Department name already exists: {}", deptName);
            return null;
        }
        //2. 修改部门名称
        Department department = new Department();
        department.setDept_id(deptId);
        department.setDept_name(deptName);
        if(deptDao.updateDeptNameById(department) == 1) {
            log.info("Updated department ID {} to new name: {}", deptId, deptName);
            // 清除缓存
            cleanCache("dept_and_num:*");
            cleanCache("Dept");
            return selectAllDeptAndNum(currentPage, pageSize);
        }
        return null;
    }

    //TODO clear_cache()
    private void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }

}

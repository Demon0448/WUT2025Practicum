package com.oa7.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa7.pojo.Department;
import com.oa7.service.EmpService;
import com.oa7.dao.EmpDao;
import com.oa7.pojo.Emp;
import com.oa7.util.JediPoolUtil;
import com.oa7.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpDao empDao;
    @Autowired
    private JediPoolUtil jediPoolUtil;

    //查询所有员工信息
    @Override
    public RESP selectByPage(int current , int size) {
        System.out.println("查看所有员工实现的分页....");
        PageHelper.startPage(current, size, true);
        List<Emp> list = empDao.selectByPageHelper();
        PageInfo<Emp> data = new PageInfo<>(list);
        return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
    }

    @Override
    public RESP getDeptDataFinDall() {
        Jedis jedis = jediPoolUtil.getJedis();
        String dept_json = jedis.get("Dept");

        //没有数据  从数据库获取
        List<Department> lists;
        if (dept_json == null || dept_json.length() == 0) {
            System.out.println("redis没有数据，从数据或许后写入redis中......");
            lists = empDao.getDeptData();
            String jsonString = JSONObject.toJSONString(lists);
            jedis.set("Dept", jsonString);
            //归还连接
            jedis.close();
        } else {
            lists = JSONObject.parseArray(dept_json, Department.class);
            System.out.println("直接查询Redis......");
        }
        return RESP.ok(lists);
    }


    //更新员工信息
    @Override
    public RESP updateEmp(Emp emp , int current , int size) {
        int r = empDao.updateEmp(emp);
        if (r > 0) {
            System.out.println("更新员工分页......");
            PageHelper.startPage(current, size, true);
            List<Emp> list = empDao.selectByPageHelper();
            PageInfo<Emp> data = new PageInfo<>(list);
            return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
        }
        return null;
    }

//    删除员工信息
    @Override
    public RESP deleteEmp(Emp emp , int current , int size) {
        int d = empDao.deleteEmpSignByNumber(emp);
        if(d>=0){
            int r = empDao.deleteEmp(emp);
            if (r > 0) {
                System.out.println("删除员工信息分页......");
                PageHelper.startPage(current, size, true);
                List<Emp> list = empDao.selectByPageHelper();
                PageInfo<Emp> data = new PageInfo<>(list);
                return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
            }
        }
        return null;
    }

//    添加员工
    @Override
    public RESP addEmp(Emp emp ,  int current , int size) {
        Emp emp1 = empDao.selectByName(emp);
        if(emp1!=null){
            return null;
        }else {
            int r = empDao.addEmp(emp);
            if (r > 0) {
                System.out.println("添加员工信息分页......");
                PageHelper.startPage(current, size, true);
                List<Emp> list = empDao.selectByPageHelper();
                PageInfo<Emp> data = new PageInfo<>(list);
                return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
            }
        }
        return null;
    }

//    获取部门列表
    @Override
    public RESP getDeptData() {
        return RESP.ok(empDao.getDeptData());
    }

//    获取职务列表
    @Override
    public RESP getDutyData() {
        return RESP.ok(empDao.getDutyData());
    }

//    更新人事信息
    @Override
    public RESP updateDD(Emp emp , int current , int size) {
        int r = empDao.updateDD(emp);
        if (r > 0) {
            System.out.println("添更新人事信息分页......");
            PageHelper.startPage(current, size, true);
            List<Emp> list = empDao.selectByPageHelper();
            PageInfo<Emp> data = new PageInfo<>(list);
            return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
        }
        return null;
    }
}

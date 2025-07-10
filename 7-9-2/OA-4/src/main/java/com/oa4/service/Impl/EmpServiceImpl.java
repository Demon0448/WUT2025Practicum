package com.oa4.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa4.pojo.Department;
import com.oa4.pojo.Duty;
import com.oa4.service.EmpService;
import com.oa4.dao.EmpDao;
import com.oa4.pojo.Emp;
import com.oa4.util.JediPoolUtil;
import com.oa4.util.RESP;
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
    private JediPoolUtil jedisPoolUtil;

    @Autowired
    private RedisTemplate redisTemplate;

//    @Override
//    public RESP selectByPage(int currentPage, int pageSize, HttpSession session) {
//        log.info("currentPage:"+currentPage+"  pageSize:"+pageSize);
//
//        //2 分页的实现
//        PageHelper.startPage(currentPage, pageSize, true);
//        List<Emp> list = empDao.selectByPageHelper();
//        PageInfo<Emp> data = new PageInfo<Emp>(list);
//
//
//        //3 返回结果
//        return RESP.ok(data.getList(), data.getPageNum(), data.getTotal());
//
//    }
    @Override
    public RESP selectByPage(int currentPage, int pageSize, HttpSession session) {
        log.info("currentPage:" + currentPage + "  pageSize:" + pageSize);

        String key = "emp:page:" + currentPage + ":size:" + pageSize;

        // 1. 先尝试从 Redis 获取数据
        PageInfo<Emp> cachedData = (PageInfo<Emp>) redisTemplate.opsForValue().get(key);
        if (cachedData != null) {
            log.info("Cache hit for key: {}", key);
            return RESP.ok(cachedData.getList(), cachedData.getPageNum(), cachedData.getTotal());
        }

        // 2. 缓存未命中，执行数据库查询
        PageHelper.startPage(currentPage, pageSize, true);
        List<Emp> list = empDao.selectByPageHelper();
        PageInfo<Emp> data = new PageInfo<>(list);

        // 3. 将结果写入 Redis，并设置过期时间（例如 5 分钟）
        if (data.getList() != null && !data.getList().isEmpty()) {
            redisTemplate.opsForValue().set(key, data);
            // 可选：设置过期时间避免脏数据堆积
            // redisTemplate.expire(key, 5, TimeUnit.MINUTES);
        } else {
            // 空结果也缓存一段时间，防止穿透
            redisTemplate.opsForValue().set(key, data);
            // redisTemplate.expire(key, 1, TimeUnit.MINUTES);
        }

        // 4. 返回结果
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
            cleanCache("emp:page:*");
            return selectByPage(currentPage,pageSize,session);
        }
        return null;
    }

//    @Override
//    public RESP getDeptList() {
//        String key = "deptList";
//        redisTemplate.opsForValue().get(key);
//        List<Department> deptList = empDao.getDeptData();
//        return RESP.ok(deptList);
//    }
    @Override
    public RESP getDeptList() {
        String key = "deptList";
        List<Department> deptList = (List<Department>) redisTemplate.opsForValue().get(key);


        // 检查缓存中是否有数据
        if (deptList == null) {
            deptList = empDao.getDeptData();
            // 将查询结果放入缓存
            redisTemplate.opsForValue().set(key, deptList);
            // 可选：设置过期时间，例如 5 分钟
            // redisTemplate.expire(key, 5, TimeUnit.MINUTES);
        }
        log.info("deptList:"+deptList);
        return RESP.ok(deptList);
    }


    @Override
    public RESP getDutyList() {
        String key = "dutyList";
        List<Duty> dutyList = (List<Duty>) redisTemplate.opsForValue().get(key);
        // 检查缓存中是否有数据
        if (dutyList == null) {
            dutyList = empDao.getDutyData();
            redisTemplate.opsForValue().set(key, dutyList);
        }
        log.info("dutyList:"+dutyList);
        return RESP.ok(dutyList);
    }

    @Override
    public RESP updateEmp(Emp emp, int currentPage, int pageSize, HttpSession session) {

        log.info("emp:"+emp);
        //边界检查需要吗

        //TODO
        Emp empAns = empDao.selectByNumber(emp);
        if(empAns == null){
            return null;
        }

        //name birth address
        empAns.setName(emp.getName());
        empAns.setBirthday(emp.getBirthday());
        empAns.setAddress(emp.getAddress());

        int i=empDao.updateEmp(empAns);
        if(i>0){

            cleanCache("emp:page:*");
            return selectByPage(currentPage,pageSize,session);

        }
        return null;
    }

    @Override
    public RESP deleteEmp(Emp emp, int currentPage, int pageSize, HttpSession session) {

        //边界检查需要吗
        int i=empDao.deleteEmp(emp);
        if(i>0){
            cleanCache("emp:page:*");
            return selectByPage(currentPage,pageSize,session);
        }
        return null;
    }

    @Override
    public RESP updateDD(Emp emp, int currentPage, int pageSize, HttpSession session) {

        log.info("emp:"+emp);
        //边界检查需要吗

        //TODO
        Emp empAns = empDao.selectByNumber(emp);
        if(empAns == null){
            return null;
        }

        empAns.setDept_id(emp.getDept_id());
        empAns.setDuty_id(emp.getDuty_id());



        int i=empDao.updateEmp(empAns);
        if(i>0){

            cleanCache("emp:page:*");
            return selectByPage(currentPage,pageSize,session);

        }
        return null;
    }

    private void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}

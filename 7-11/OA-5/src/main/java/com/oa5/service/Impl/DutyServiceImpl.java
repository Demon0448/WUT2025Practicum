package com.oa5.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa5.dao.DutyDao;
import com.oa5.pojo.Department;
import com.oa5.pojo.Duty;
import com.oa5.service.DutyService;
import com.oa5.util.RESP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class DutyServiceImpl implements DutyService {
    @Autowired
    private DutyDao dutyDao;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public RESP selectAllDutyAndNum(Integer currentPage, Integer pageSize) {
        //key: dept_and_num redis
        String key = "duty_and_num:page:" + currentPage + ":size:" + pageSize;
        // 1. 先尝试从 Redis 获取数据
        PageInfo<Duty>data=(PageInfo<Duty>) redisTemplate.opsForValue().get(key);
        if (data == null) {
            log.info("Redis cache miss for key: {}", key);
            // 2. 缓存未命中，执行数据库查询
            PageHelper.startPage(currentPage, pageSize, true);
            List<Duty> list = dutyDao.selectAllDutyAndNum();
            data = new PageInfo<>(list);
            // 3. 将结果写入 Redis，并设置过期时间（例如 5 分钟）
            redisTemplate.opsForValue().set(key, data);

        }
        // 4. 返回结果
        return RESP.ok( data.getList(), data.getPageNum(), (int)data.getTotal());
    }

    @Override
    public RESP updateDutyNameById(Duty duty, Integer currentPage, Integer pageSize) {
        //1.检查是否重名称
        Duty existingDuty = dutyDao.selectByName(duty);
        if (existingDuty != null) {
            return null;
        }
        //2.更新
        int result = dutyDao.updateDutyNameById(duty);
        if (result > 0) {
            //更新成功，清除缓存
            cleanCache("duty_and_num:*");
            cleanCache("Duty");
            //重新查询并返回结果 函数复用
            return selectAllDutyAndNum(currentPage, pageSize);
        }

        return null;
    }

    @Override
    public RESP addDuty(Duty duty, Integer currentPage, Integer pageSize) {
        //1.检查是否重名称
        Duty existingDuty = dutyDao.selectByName(duty);
        if (existingDuty != null) {
            return null;
        }
        //2.添加
        int result = dutyDao.addDuty(duty);
        if (result > 0) {
            //添加成功，清除缓存

            cleanCache("duty_and_num:*");
            cleanCache("Duty");
            //重新查询并返回结果 函数复用
            return selectAllDutyAndNum(currentPage, pageSize);
        }
        return null;
    }


    private void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        Long delete = redisTemplate.delete(keys);
        log.info("Deleted {} keys matching pattern: {}", delete, pattern);
    }
}

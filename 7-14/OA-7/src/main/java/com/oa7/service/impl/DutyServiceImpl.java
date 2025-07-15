package com.oa7.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa7.dao.DutyDao;
import com.oa7.pojo.Duty;
import com.oa7.service.DutyService;
import com.oa7.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DutyServiceImpl implements DutyService {

    @Autowired
    private DutyDao dutyDao;


    @Autowired
    private RedisTemplate redisTemplate;


//    每个职务的人数
    @Override
    public RESP selectAllDutyAndNum(int current , int size) {

        //TODO  是否分页但是共用接口 约定-1 为 不分页查询
        if (current < 0 || size < 0) {
            List<Duty> list = dutyDao.selectAll();
            int count = dutyDao.countDuty();
            return RESP.ok(list, 1, count);
        }

        System.out.println("查看职务列表实现分页....");
        PageHelper.startPage(current, size, true);
        List<Duty> list = dutyDao.selectByPageHelper();
        PageInfo<Duty> data = new PageInfo<>(list);
        return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
    }
//更新职务名称
    @Override
    public RESP updateDutyNameById(Duty duty , int current , int size){
        Duty duty1 = dutyDao.selectByName(duty);
        if (duty1 == null) {
            int r = dutyDao.updateDutyNameById(duty);
            if (r > 0) {
                System.out.println("更新职务名称表实现分页....");
                PageHelper.startPage(current, size, true);
                List<Duty> list = dutyDao.selectByPageHelper();
                PageInfo<Duty> data = new PageInfo<>(list);
                return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
            }
        }
        return null;
    }
//添加职务
    @Override
    public RESP addDuty(String dutyName) {

        //TODO 懒得改前端了，后端搞吧
        JSONObject jsonObject = JSONObject.parseObject(dutyName);
        dutyName = jsonObject.getString("duty_name");

        Duty duty = new Duty();
        duty.setDuty_name(dutyName);
        Duty duty1 = dutyDao.selectByName(duty);

        if (duty1 == null) {
            int r = dutyDao.addDuty(duty);
            if (r > 0) {
                cleanCache("duty");
                return RESP.ok("添加成功");
            }
        }
        return null;
    }

    @Override
    public RESP updateDutyNameById(String dutyId, String dutyName) {
        //TODO 懒得改前端了，后端搞吧
        JSONObject jsonObject = JSONObject.parseObject(dutyName);
        dutyName = jsonObject.getString("duty_name");


        Duty duty = new Duty();
        duty.setDuty_id(Integer.parseInt(dutyId));
        duty.setDuty_name(dutyName);
        int res = dutyDao.updateDutyNameById(duty);
        if (res > 0) {
            cleanCache("duty");
            return RESP.ok("修改成功");
        }
        return null;
    }


    private void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}

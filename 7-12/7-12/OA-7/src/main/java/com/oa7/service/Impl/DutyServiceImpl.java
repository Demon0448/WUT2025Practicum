package com.oa7.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa7.pojo.Department;
import com.oa7.service.DutyService;
import com.oa7.dao.DutyDao;
import com.oa7.pojo.Duty;
import com.oa7.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DutyServiceImpl implements DutyService {

    @Autowired
    private DutyDao dutyDao;

//    每个职务的人数
    @Override
    public RESP selectAllDutyAndNum(int current , int size) {
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
    public RESP addDuty(Duty duty , int current, int size) {
        Duty duty1 = dutyDao.selectByName(duty);
        System.out.println();
        if (duty1 == null) {
            int r = dutyDao.addDuty(duty);
            if (r > 0) {
                System.out.println("添加职务实现分页....");
                PageHelper.startPage(current, size, true);
                List<Duty> list = dutyDao.selectByPageHelper();
                PageInfo<Duty> data = new PageInfo<>(list);
                return RESP.ok(data.getList(), data.getPageNum(), (int) data.getTotal());
            }
        }
        return null;
    }
}

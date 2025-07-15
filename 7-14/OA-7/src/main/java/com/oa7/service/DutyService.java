package com.oa7.service;

import com.oa7.pojo.Duty;
import com.oa7.util.RESP;

public interface DutyService {
    //查询所有职务和人数
    RESP selectAllDutyAndNum(int a ,  int b);
//更新职务的名字
    RESP updateDutyNameById(Duty duty,int a ,  int b);
//添加新的职务
    RESP addDuty(String dutyName);

    RESP updateDutyNameById(String dutyId, String dutyName);
}

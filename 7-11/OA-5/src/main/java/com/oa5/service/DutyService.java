package com.oa5.service;

import com.oa5.pojo.Duty;
import com.oa5.util.RESP;

public interface DutyService {
    RESP selectAllDutyAndNum(Integer currentPage, Integer pageSize);

    RESP updateDutyNameById(Duty duty, Integer currentPage, Integer pageSize);

    RESP addDuty(Duty duty, Integer currentPage, Integer pageSize);
}

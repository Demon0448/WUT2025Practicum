package com.oa5.service;

import com.oa5.pojo.Sign;
import com.oa5.util.RESP;

public interface SignService {
    RESP selectDaySignList(Integer currentPage, Integer pageSize);

    RESP selectYesByPage(Integer currentPage, Integer pageSize);

    RESP selectNoByPage(Integer currentPage, Integer pageSize);

    RESP selectToDayYesByPage(Integer currentPage, Integer pageSize);

    RESP updateStateYes(Sign sign,int currentPage, int pageSize);

    RESP updateStateNo(Sign sign, Integer currentPage, Integer pageSize);

    RESP selectToDayNoByPage(Integer currentPage, Integer pageSize);

    RESP selectImgSignList(Integer currentPage, Integer pageSize);

    RESP searchByEmployeeNumberAndState(String employeeNumber, String  state,Integer currentPage, Integer pageSize);

}

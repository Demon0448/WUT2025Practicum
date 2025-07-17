package com.oa7.service;

import com.oa7.util.RESP;

public interface DeptService {
    RESP selectAllDeptAndNum(int currentPage, int pageSize);

    RESP insertDept(String deptName, int currentPage, int pageSize);

    RESP updateDeptNameById(String dept_id, String deptName, int currentPage, int pageSize);

    String selectDeptById(String deptId);
}

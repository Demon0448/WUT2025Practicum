package com.oa5.service;

import com.oa5.pojo.Department;
import com.oa5.util.RESP;

import java.util.List;

public interface DeptService {
    RESP selectAllDeptAndNum(int currentpage, int pagesize);

    RESP addDept(String deptName, Integer currentPage, Integer pageSize);

    RESP updateDeptNameById(Integer deptId, String deptName, Integer currentPage, Integer pageSize);
}

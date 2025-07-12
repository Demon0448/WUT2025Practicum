package com.oa7.service;

import com.oa7.pojo.Department;
import com.oa7.util.RESP;


public interface DeptService {
//查询所有部门和人数
    RESP selectAllDeptAndNum(int a , int b);
//更新部门信息
    RESP updateDeptNameById(Department department,int a , int b);
//添加新的部门
    RESP addDept(Department department,int a , int b);
}

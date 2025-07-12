package com.oa7.service;

import com.oa7.pojo.Emp;
import com.oa7.util.RESP;


public interface EmpService {

    //查询所有员工信息
    RESP selectByPage(int a , int b);

    RESP getDeptDataFinDall();

    //更新员工信息
    RESP updateEmp(Emp emp , int a , int b);

    //删除员工信息
    RESP deleteEmp(Emp emp , int a , int b);

    //添加员工
    RESP addEmp(Emp emp , int a , int b);

    //获取部门列表
    RESP getDeptData();

    //获取职务列表
    RESP getDutyData();

    //更新员人事信息
    RESP updateDD(Emp emp , int a , int b);


}

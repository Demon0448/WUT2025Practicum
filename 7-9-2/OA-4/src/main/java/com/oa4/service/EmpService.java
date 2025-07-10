package com.oa4.service;

import com.github.pagehelper.Page;
import com.oa4.pojo.Department;
import com.oa4.pojo.Emp;
import com.oa4.util.RESP;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 员工管理业务逻辑接口
 * @author hp
 * @date 2025/06/12
 */
public interface EmpService {


    RESP selectByPage(int currentPage, int pageSize, HttpSession session);

    RESP addEmp(Emp emp, int currentPage, int pageSize, HttpSession session);

    RESP getDeptList();

    RESP getDutyList();

    RESP updateEmp(Emp emp, int currentPage, int pageSize, HttpSession session);

    RESP deleteEmp(Emp emp, int currentPage, int pageSize, HttpSession session);

    RESP updateDD(Emp emp, int currentPage, int pageSize, HttpSession session);
}

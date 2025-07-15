package com.oa7.dao;

import com.oa7.pojo.Emp;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmpDao {

    //通过员工编号查找员工信息
    @Select("select emp.*,dept_name,duty_name from " +
            "day.emp left join department on department.dept_id = emp.dept_id " +
            "left join duty on  emp.duty_id = duty.duty_id where number=#{number}  ")
    Emp selectByNumber(Emp emp);

    //通过员工编号直接查找员工信息
    @Select("select emp.*,dept_name,duty_name from " +
            "day.emp left join department on department.dept_id = emp.dept_id " +
            "left join duty on  emp.duty_id = duty.duty_id where number=#{number}  ")
    Emp selectByEmpNumber(int number);

    //通过员工编号更新员工信息
    @Update("update day.emp set name=#{name} ,birthday=#{birthday} ,address=#{address} where number=#{number} ")
    int updateEmp(Emp emp);

    //通过员工编号更新员工密码
    @Update("update day.emp set pwd=#{pwd}  where number=#{number} ")
    int updateEmpPwd(Emp emp);

    //获取每个员工的编号
    @Select("select number from day.emp")
    List<Integer> selectAllEmpNumber();


    @Select("select emp.*,dept_name,duty_name from " +
            "day.emp left join department on department.dept_id = emp.dept_id " +
            "left join duty on emp.duty_id = duty.duty_id order by number ")
    List<Emp> selectByPageHelper();


    //添加员工
    @Insert("insert into day.emp (name, birthday, address, dept_id, duty_id) VALUES (#{name} ,#{birthday} ,#{address} ,#{dept_id} ,#{duty_id}  )")
    int addEmp(Emp emp);

    @Select("select * from day.emp where name=#{name}   ")
    Emp selectByName(Emp emp);

    //删除员工信息
    @Delete("delete from day.emp where number=#{number} ")
    int deleteEmpByNumber(Emp emp);

    //统计员工数量
    @Select("select count(*) from day.emp")
    int countEmp();
}

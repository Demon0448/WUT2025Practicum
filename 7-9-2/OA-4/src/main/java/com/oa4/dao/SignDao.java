package com.oa4.dao;

import com.oa4.pojo.Emp;
import com.oa4.pojo.Sign;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface SignDao {

    @Select("select sign.*,dept_name,name from day.sign " +
            "left join day.emp e on e.number = sign.number " +
            "left join day.department d on d.dept_id = e.dept_id  " +
            "where day.sign.number=#{emp.number} " +
            "order by id DESC limit #{a},#{b} ")
    List<Sign> selectByPage(@Param("emp") Emp emp , @Param("a") int a , @Param("b") int b);

    @Select("select count(*) from day.sign where number=#{number} ")
    int countSignByNumber(Emp emp);

    @Select("select sign.*,dept_name,name from day.sign " +
            "left join day.emp e on e.number = sign.number " +
            "left join day.department d on d.dept_id = e.dept_id " +
            "where day.sign.number=#{emp.number}   " +
            "and signDate like concat(#{today},'%' )")
    List<Sign> selectEmpSign(@Param("emp") Emp emp , @Param("today") String today);

    @Insert("insert into day.sign(signDate,number,state,type) values (#{signDate} ,#{number} ,#{state} ,#{type} )")
    int addSign(Sign sign);

    @Update("update day.sign set state=#{sign.state}  ,signDate=#{date},sign_address=#{sign.sign_address}   where number=#{sign.number}  and signDate=#{sign.signDate}   ")
    int updateState(@Param("sign") Sign sign , @Param("date") String date);
}

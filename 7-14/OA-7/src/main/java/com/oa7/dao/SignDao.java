package com.oa7.dao;



import com.oa7.pojo.Emp;
import com.oa7.pojo.Sign;
import com.oa7.pojo.SignCountDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SignDao {


//    SELECT
//    DATE(STR_TO_DATE(signDate, '%Y-%m-%d %H:%i:%s:%f')) AS signDay,
//    COUNT(CASE WHEN state = '已签到' AND type = 'a' THEN 1 END) AS countYes,
//    COUNT(CASE WHEN state = '未签到' AND type = 'a' THEN 1 END) AS countNo
//    FROM sign
//    GROUP BY signDay
//    ORDER BY signDay DESC;


    @Select("select * from day.sign where signDate like concat(#{date},'%' )")
    List<Sign> selectByDate(String date);


    //TODO 很重要！！！

    //将上方的SQL语句转换为MyBatis注解形式
    @Select("SELECT " +
            "DATE(STR_TO_DATE(signDate, '%Y-%m-%d %H:%i:%s')) AS date, " +
            "COUNT(CASE WHEN state = '已签到' AND type = 'a' THEN 1 END) AS yc, " +
            "COUNT(CASE WHEN state = '未签到' AND type = 'a' THEN 1 END) AS nc " +
            "FROM day.sign " +
            "GROUP BY date " +
            "ORDER BY date DESC")
    List<SignCountDTO> selectSignCountByDay();

    //查询今日已签到的员工的考勤信息 不要limit
    @Select("select sign.*,dept_name,name from day.sign " +
            "left join day.emp e on e.number = sign.number " +
            "left join day.department d on d.dept_id = e.dept_id  " +
            "where day.sign.signDate like concat(#{today},'%' )" +
            "and day.sign.state='已签到'" +
            "order by id")
    List<Sign> selectYesTargetDayByPage(@Param("today") String today);




    //查询今日未签到的员工的考勤信息 不要limit
    @Select("select sign.*,dept_name,name from day.sign " +
            "left join day.emp e on e.number = sign.number " +
            "left join day.department d on d.dept_id = e.dept_id  " +
            "where day.sign.signDate like concat(#{today},'%' )" +
            "and day.sign.state='未签到'" +
            "order by id")
    List<Sign> selectNoTargetDayByPage(@Param("today") String day);

    //指定日期的已签到记录
    //查询指定一天的签到统计数据
    //将上方的SQL语句转换为MyBatis注解形式
    @Select("SELECT " +
            "DATE(STR_TO_DATE(signDate, '%Y-%m-%d %H:%i:%s')) AS signDay, " +
            "COUNT(CASE WHEN state = '已签到' AND type = 'a' THEN 1 END) AS yc, " +
            "COUNT(CASE WHEN state = '未签到' AND type = 'a' THEN 1 END) AS nc " +
            "FROM day.sign " +
            "WHERE signDate LIKE CONCAT(#{day}, '%') " +
            "GROUP BY signDay " +
            "ORDER BY signDay DESC")
    SignCountDTO selectByDay(String day);

    
    
    
    
    
    
    //查询所有出勤记录
    @Select("select * from day.sign order by sign.id")
    List<Sign> selectAll();



    //统计今日已签到人数
    @Select("select count(*) from day.sign where state='已签到' and sign.type='a' and signDate like concat(#{day},'%' ) ")
    int countByDayByStateYes(@Param("day") String day);


    //查询某个员工考勤信息
    @Select("select sign.*,dept_name,name from day.sign " +
            "left join day.emp e on e.number = sign.number " +
            "left join day.department d on d.dept_id = e.dept_id  " +
            "where day.sign.number=#{emp.number} " +
            "order by id limit #{a},#{b} ")
    List<Sign> selectByPage(@Param("emp") Emp emp , @Param("a") int a , @Param("b") int b);

    //查询已签到的员工考勤信息
    @Select("select sign.*,dept_name,name from day.sign " +
            "left join day.emp e on e.number = sign.number " +
            "left join day.department d on d.dept_id = e.dept_id  " +
            "where day.sign.state='已签到' " +
            "order by id DESC limit #{a},#{b} ")
    List<Sign> selectYesByPage(@Param("a") int a , @Param("b") int b);

    //查询已签到的员工考勤信息 不要limit
    @Select("select sign.*,dept_name,name from day.sign " +
            "left join day.emp e on e.number = sign.number " +
            "left join day.department d on d.dept_id = e.dept_id  " +
            "where day.sign.state='已签到' " +
            "order by id DESC")
    List<Sign> selectYes();



    //查询未签到的员工考勤信息 不要limit
    @Select("select sign.*,dept_name,name from day.sign " +
            "left join day.emp e on e.number = sign.number " +
            "left join day.department d on d.dept_id = e.dept_id  " +
            "where day.sign.state='未签到' " +
            "order by id DESC")
    List<Sign> selectNo();



    //查询未签到的员工考勤信息
    @Select("select sign.*,dept_name,name from day.sign " +
            "left join day.emp e on e.number = sign.number " +
            "left join day.department d on d.dept_id = e.dept_id  " +
            "where day.sign.state='未签到' " +
            "order by id DESC limit #{a},#{b} ")
    List<Sign> selectNoByPage(@Param("a") int a , @Param("b") int b);

    //查询所有员工的考勤信息
    @Select("select sign.*,dept_name,name from day.sign " +
            "left join day.emp e on e.number = sign.number " +
            "left join day.department d on d.dept_id = e.dept_id  " +
            "order by id limit #{a},#{b} ")
    List<Sign> selectAllByPage(@Param("a") int a , @Param("b") int b);

    //查询今日已签到的员工的考勤信息
    @Select("select sign.*,dept_name,name from day.sign " +
            "left join day.emp e on e.number = sign.number " +
            "left join day.department d on d.dept_id = e.dept_id  " +
            "where day.sign.signDate like concat(#{today},'%' )" +
            "and day.sign.state='已签到'" +
            "order by id limit #{a},#{b} ")
    List<Sign> selectToDayYesByPage(@Param("a") int a , @Param("b") int b , @Param("today") String today);

    //查询今日未签到的员工的考勤信息
    @Select("select sign.*,dept_name,name from day.sign " +
            "left join day.emp e on e.number = sign.number " +
            "left join day.department d on d.dept_id = e.dept_id  " +
            "where day.sign.signDate like concat(#{today},'%' )" +
            "and day.sign.state='未签到'" +
            "order by id limit #{a},#{b} ")
    List<Sign> selectToDayNoByPage(@Param("a") int a , @Param("b") int b , @Param("today") String today);

    //统计所有员工考勤数
    @Select("select count(*) from day.sign")
    int countUser();

    //统计某个员工的考勤数
    @Select("select count(*) from day.sign where number=#{number} ")
    int countSignByNumber(Emp emp);


    //统计今日未签到人数
    @Select("select count(*) from day.sign where state='未签到' and sign.type='a' and signDate like concat(#{day},'%' ) ")
    int countByDayByStateNo(@Param("day") String day);

    //统计已签到的员工数
    @Select("select count(*) from day.sign where state='已签到'")
    int countUserYes();

    //统计未签到的员工数
    @Select("select count(*) from day.sign where state='未签到'")
    int countUserNo();

    //统计今日已签到的员工数
    @Select("select count(*) from day.sign where state='已签到' and day.sign.signDate like concat(#{today},'%' ) ")
    int countToDayYes(@Param("today") String today);

    //统计今日未签到的员工数
    @Select("select count(*) from day.sign where state='未签到' and day.sign.signDate like concat(#{today},'%' ) ")
    int countToDayNo(@Param("today") String today);

    //查询某个员工某天的考勤信息
    @Select("select sign.*,dept_name,name from day.sign " +
            "left join day.emp e on e.number = sign.number " +
            "left join day.department d on d.dept_id = e.dept_id " +
            "where day.sign.number=#{emp.number}   " +
            "and signDate like concat(#{today},'%' )")
    List<Sign> selectEmpSign(@Param("emp") Emp emp , @Param("today") String today);

    //    添加考勤任务
    @Insert("insert into day.sign(signDate,number,state,type) values (#{signDate} ,#{number} ,#{state},#{type}  )")
    int addSign(Sign sign);

    //    更新考勤状态
    @Update("update day.sign set state=#{sign.state}  ,signDate=#{date}  ,sign_address=#{sign.sign_address} where number=#{sign.number}  and signDate=#{sign.signDate}   ")
    int updateState(@Param("sign") Sign sign , @Param("date") String date);

//    @Delete("DELETE  from day.sign  WHERE signDate  BETWEEN  signDate=#{beganDate} and signDate=#{endDate}")
//    void deleteSign(String beganDate,String endDate);

    @Select("select sign.* from day.sign " +
            "where day.sign.number=#{employeeNumber} " +
            "and day.sign.state=#{state} " +
            "order by id DESC")
    List<Sign> selectByEmployeeNumberAndState(@Param("employeeNumber") String employeeNumber,
                                              @Param("state") String state);

    @Select("select count(*) from day.sign where number=#{employeeNumber}")
    int countByEmployeeNumber(@Param("employeeNumber") String employeeNumber);


}

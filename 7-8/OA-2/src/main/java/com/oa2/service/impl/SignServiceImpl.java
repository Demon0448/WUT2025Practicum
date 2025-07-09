package com.oa2.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa2.service.SignService;
import com.oa2.dao.EmpDao;
import com.oa2.dao.SignDao;
import com.oa2.pojo.Emp;
import com.oa2.pojo.Sign;
import com.oa2.util.DU;
import com.oa2.util.IpToAddressUtil;
import com.oa2.util.LocationUtil;
import com.oa2.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class SignServiceImpl implements  SignService{

    @Autowired
    private SignDao signDao;
    @Autowired
    private EmpDao empDao;



    public RESP empSignList(HttpSession session){
        //1  获取员工信息(session)//121
        Emp emp = (Emp) session.getAttribute("emp");

        //2 获取当天的日期
        String today= DU.getNowSortString();//2025-07-08
        //查询是否已存在当天的打卡记录

        List<Sign> signList = signDao.selectEmpSign(emp,today);
        //说明还没有当天的打卡记录 需要重新创建
        if(signList.size()==0){
            //查询出所有员工的工号(当成条件 进行Inster)
            List<Integer> list1= empDao.selectAllEmpNumber();//查询出所有员工的工号

            for (int i:list1){//121 122 123 124
                Sign sign=new Sign();
                sign.setSignDate(DU.getNowAM());//2025-07-08 08:30:00:00
                sign.setNumber(i);//124
                sign.setState("未签到");//
                sign.setType("a");//
                signDao.addSign(sign);

                sign.setSignDate(DU.getNowPM());
                sign.setType("p");
                signDao.addSign(sign);
            }

            signList = signDao.selectEmpSign(emp,today);
        }
        return RESP.ok(signList);
    }

    @Override
    public RESP updateState(Sign sign, HttpSession session, String cor) {
        Emp emp = (Emp) session.getAttribute("emp");
        if (emp == null) {
            return null;
        }

        // 可选：校验是否为当前用户操作
        if (sign.getNumber()==emp.getNumber()) {
            return null;
        }


        // 腾讯地图API获取地理位置
        String location = LocationUtil.getAddressFromCoordinates(cor);
        if (location == null) {
            return null;
        }
        // 设置签到信息


        sign.setNumber(emp.getNumber());
        sign.setSignDate(DU.getNowAM()); // 设置签到时间为当前时间
        sign.setState("已签到");         // 设置签到状态为已签到
        sign.setSign_address(location);




        // 执行更新

        //TODO
        
        int rows = signDao. updateState(sign, sign.getState());
        if (rows <= 0) {
            return RESP.error("签到失败，请重试");
        }

        return RESP.ok("签到成功");
        }

}

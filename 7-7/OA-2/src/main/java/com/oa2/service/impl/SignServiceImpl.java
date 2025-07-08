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
import com.oa2.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class SignServiceImpl implements SignService {


    @Autowired
    private SignDao signDao;
    @Autowired
    private EmpDao empDao;

    @Override
    public RESP empSignList(HttpSession session) {
        //获取当前登录的员工
        Emp emp = (Emp) session.getAttribute("emp");

        String today= DU.getNowSortString();

        List<Sign> signList=signDao.selectEmpSign(emp,today);




        if(signList == null || signList.isEmpty()) {
            //查询所有员工工号
            List<Integer> empNumbers = empDao.selectAllEmpNumber();

            for(Integer empNumber : empNumbers) {
                System.out.println("员工工号：" + empNumber);

                //如果员工没有打卡记录，则添加一条默认记录
                Sign sign = new Sign();
                sign.setNumber(empNumber);
                sign.setState("未签到");
                sign.setSignDate(DU.getNowAM());
                sign.setType("a");
                signDao.addSign(sign);

                sign.setSignDate(DU.getNowPM());
                sign.setType("p");
                signDao.addSign(sign);
            }
            //重新查询打卡记录
            signList = signDao.selectEmpSign(emp, today);
        }

        return RESP.ok(signList);

    }

    @Override
    public RESP updateState(Sign sign, HttpSession session, String cor) {
        //获取当前登录的员工
        Emp emp = (Emp) session.getAttribute("emp");

        System.out.println("经纬度"+cor);

        return null;

    }
}

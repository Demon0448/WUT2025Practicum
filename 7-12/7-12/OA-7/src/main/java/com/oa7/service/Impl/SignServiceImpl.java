package com.oa7.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa7.service.SignService;
import com.oa7.dao.EmpDao;
import com.oa7.dao.SignDao;
import com.oa7.pojo.Emp;
import com.oa7.pojo.O;
import com.oa7.pojo.Sign;
import com.oa7.util.DU;
import com.oa7.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    //检查考勤的状态
    public List<Sign> check(List<Sign> list) {
        List<Sign> list1 = new ArrayList<>();
        for (Sign sign : list) {
            if (sign.getState().equals("未签到")) {
                if (sign.getSignDate().contains("08:30:00:00")) {
                    if (DU.IsAMMillis(System.currentTimeMillis())) {
                        sign.setTag(1);
                    } else {
                        sign.setTag(0);
                    }
                } else if (sign.getSignDate().contains("14:30:00:00")) {
                    if (DU.IsPMMillis(System.currentTimeMillis())) {
                        sign.setTag(1);
                    } else {
                        sign.setTag(0);
                    }
                }
            } else if (sign.getState().equals("已签到")) {
                sign.setTag(0);
            }
            list1.add(sign);
        }
        return list1;
    }


    //查询某个员工所有考勤信息
    @Override
    public RESP selectByPage(int current, int size, HttpSession session) {
        System.out.println("签到实现分页查询.......");
        Emp emp = (Emp) session.getAttribute("emp");
        // 使用 PageHelper 开始分页
        PageHelper.startPage(current, size, true);
        // 查询数据（PageHelper 会自动拦截接下来的第一个查询进行分页）
        List<Sign> list = signDao.selectByPage(emp); // 注意这里传 0 和 MAX_VALUE
        // 获取分页信息
        PageInfo<Sign> pageInfo = new PageInfo<>(list);
        // 获取总记录数
        long total = pageInfo.getTotal();
        // 返回分页结果
        return RESP.ok(list, current, (int) total);
    }

    //查询已签到的考勤信息
    @Override
    public RESP selectYesByPage(int a, int b) {
        int c = a;
        a = (a - 1) * b;
        List<Sign> list = signDao.selectYesByPage(a, b);
        int all = signDao.countUserYes();
        String now = DU.getNowSortString();
        for (Sign sign : list) {
            if (sign.getSignDate().contains(now)) {
                sign.setTag(1);
            } else {
                sign.setTag(0);
            }
        }
        return RESP.ok(list, c, all);
    }


    //查询未签到的考勤信息
    @Override
    public RESP selectNoByPage(int a, int b) {
        int c = a;
        a = (a - 1) * b;
        List<Sign> list = signDao.selectNoByPage(a, b);
        int all = signDao.countUserNo();
        String now = DU.getNowSortString();
        for (Sign sign : list) {
            if (sign.getSignDate().contains(now)) {
                sign.setTag(1);
            } else {
                sign.setTag(0);
            }
        }
        return RESP.ok(list, c, all);
    }

    //查询所有考勤信息
    @Override
    public RESP selectAllByPage(int a, int b) {
        int c = a;
        a = (a - 1) * b;
        List<Sign> list = signDao.selectAllByPage(a, b);
        int all = signDao.countUser();
        return RESP.ok(list, c, all);
    }

    //查询今日已签到的考勤信息
    @Override
    public RESP selectToDayYesByPage(int a, int b) {
        int c = a;
        a = (a - 1) * b;
        List<Sign> list = signDao.selectToDayYesByPage(a, b, DU.getNowSortString());
        int all = signDao.countToDayYes(DU.getNowSortString());
        String now = DU.getNowSortString();
        for (Sign sign : list) {
            if (sign.getSignDate().contains(now)) {
                sign.setTag(1);
            } else {
                sign.setTag(0);
            }
        }
        return RESP.ok(list, c, all);
    }

    //查询今日未签到的考勤信息
    @Override
    public RESP selectToDayNoByPage(int a, int b) {
        int c = a;
        a = (a - 1) * b;
        List<Sign> list = signDao.selectToDayNoByPage(a, b, DU.getNowSortString());
        int all = signDao.countToDayNo(DU.getNowSortString());
        String now = DU.getNowSortString();
        for (Sign sign : list) {
            if (sign.getSignDate().contains(now)) {
                sign.setTag(1);
            } else {
                sign.setTag(0);
            }
        }
        return RESP.ok(list, c, all);
    }

    //查询今日的考勤信息
    @Override
    public RESP selectDaySignList(int a, int b) {
        int c = a;
        a = (a - 1) * b;
        List<String> list = new ArrayList<>();
        List<Sign> list1 = signDao.selectAll();
        if (list1.size() > 0) {
            for (Sign sign : list1) {
                String str = sign.getSignDate();
                String[] arr = str.split("\\s+");
                list.add(arr[0]);
            }
            List<String> list2 = list.stream().distinct().collect(Collectors.toList());
            List<O> Olist = new ArrayList<>();
            for (String day : list2) {
                O o = new O();
                o.setDate(day);
                o.setYc(signDao.countByDayByStateYes(day));
                o.setNc(signDao.countByDayByStateNo(day));
                Olist.add(o);
            }
            return RESP.ok(Olist, c, list2.size());
        } else {
            return null;
        }
    }

    //查询某个员工今日考勤信息
    @Override
    public RESP empSignList(HttpSession session) {
        Emp emp = (Emp) session.getAttribute("emp");
        String today = DU.getNowSortString();
        List<Sign> list = signDao.selectEmpSign(emp, today);
        if (list.size() == 0) {
            //测试方法，服务器部署后可被定时任务代替
            //获取所有员工编号
            List<Integer> list1 = empDao.selectAllEmpNumber();
            for (int n : list1) {
                Sign sign = new Sign();
                sign.setSignDate(DU.getNowAM());
                sign.setNumber(n);
                sign.setState("未签到");
                sign.setType("a");
                signDao.addSign(sign);
                sign.setSignDate(DU.getNowPM());
                sign.setType("p");
                signDao.addSign(sign);
            }
            list = signDao.selectEmpSign(emp, today);
        }
        return RESP.ok(check(list));
    }

    //更新员工考勤状态
    @Override
    public RESP updateState(Sign sign, HttpSession session) {
        int a = signDao.updateState(sign, DU.getNowString());
        Emp emp = (Emp) session.getAttribute("emp");
        String today = DU.getNowSortString();
        List<Sign> list = signDao.selectEmpSign(emp, today);
        if (a > 0) {
            return RESP.ok(check(list));
        }
        return null;
    }

    //补签
    @Override
    public RESP updateStateYes(Sign sign, int a, int b) {
        int r = signDao.updateState(sign, DU.getNowString());
        if (r > 0) {

            int c = a;
            a = (a - 1) * b;
            List<Sign> list = signDao.selectYesByPage(a, b);
            int all = signDao.countUserYes();
            String now = DU.getNowSortString();
            for (Sign sign1 : list) {
                if (sign1.getSignDate().contains(now)) {
                    sign1.setTag(1);
                } else {
                    sign1.setTag(0);
                }
            }
            return RESP.ok(list, c, all);
        }
        return null;
    }

    //驳回申请
    @Override
    public RESP updateStateNo(Sign sign, int a, int b) {
        int r = signDao.updateState(sign, DU.getNowString());
        if (r > 0) {
            int c = a;
            a = (a - 1) * b;
            List<Sign> list = signDao.selectNoByPage(a, b);
            int all = signDao.countUserNo();
            String now = DU.getNowSortString();
            for (Sign sign1 : list) {
                if (sign1.getSignDate().contains(now)) {
                    sign1.setTag(1);
                } else {
                    sign1.setTag(0);
                }
            }
            return RESP.ok(list, c, all);
        }
        return null;
    }

    //近五日签到统计视图
    public RESP selectImgSignList() {
        List<String> list = new ArrayList<String>();
        //1查询出所有员工的考情记录
        List<Sign> list1 = signDao.selectAll();
        if (list1.size() > 0) {
            for (Sign sign : list1) {
                String str = sign.getSignDate();
                String[] arr = str.split("\\s+");
                list.add(arr[0]);//2025-07-10
            }
            //提取签到日期 并且去重2020-07-10——2025-07-12
            List<String> list2 = list.stream().distinct().collect(Collectors.toList());
            //提取最近五天
            List<String> list3 = list2.subList(Math.max(list2.size() - 5, 0), list2.size());

            //统计总人数
            int count = empDao.countUser();
            List<Integer> listYc = new ArrayList<>();//已签到人数
            List<Integer> listNc = new ArrayList<>();//已签到人数
            List<Integer> listNe = new ArrayList<>();//已签到人数

            for(String day:list3){//2025-07-06循环便利最近五天的集合
                listYc.add(signDao.countByDayByStateYes(day));
                listNc.add(signDao.countByDayByStateNo(day));
                listNe.add(count);
            }
            //             日期   已签到   未签到   总人数
            return RESP.ok(list3,listYc,listNc,listNe);
        }
        return null;
    }

    @Override
    public void deleteSign(String beganDate, String endDate) {
        signDao.deleteSign(beganDate, endDate);
        System.out.println("删除成功!!!");
    }
}

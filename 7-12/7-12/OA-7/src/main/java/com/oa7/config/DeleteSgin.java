package com.oa7.config;

import com.oa7.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

/**
 *   定期删除上个月的打卡记录
 */
public class DeleteSgin {
    @Autowired
    private SignService signService;

    //秒   分   时   日     月  年
    @Scheduled(cron = "0 0 0 15 * *")
    public void deleteSgin(){
        //获取当前时间
        LocalDate toDay =LocalDate.now();
        //获取上个月的起始日期
        LocalDate start = toDay.withDayOfMonth(1).minusMonths(1);
        LocalDate end  =toDay.withDayOfMonth(1).minusDays(1);

        signService.deleteSign(start.toString(),end.toString());
    }


    public static void main(String[] args) {

        LocalDate toDay =LocalDate.of(2025,3,1);
        //LocalDate toDay =LocalDate.now();
        System.out.println(toDay.getMonth());//获取当前月份
        System.out.println(toDay.getDayOfWeek());//获取星期几
        //System.out.println(toDay.getDayOfYear());//获取第几天
        System.out.println("-------------------------");
        LocalDate start = toDay.withDayOfMonth(1).minusMonths(1);
        LocalDate end  =toDay.withDayOfMonth(1).minusDays(1);
        System.out.println(start);
        System.out.println(end);
    }
}

package com.oa5.util;

import lombok.Data;

/**
 * @name: 余仕贤
 * @Date: 2021/11/30 22:08
 * @Author: IAO
 * @Description: 返回数据的封装类
 */
@Data
public class RESP {

    private Object data;
    private Object data1;
    private Object data2;
    private Object data3;
    private Object data4;
    private int pageNum;
    private int total;

    public RESP(Object data , int pageNum , int total) {
        this.data = data;
        this.pageNum = pageNum;
        this.total = total;
    }

    public RESP(Object data , Object data1 , Object data2 , Object data3) {
        this.data = data;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }



    public RESP(Object data) {
        this.data = data;
    }

    public RESP(Object data , Object data1) {
        this.data = data;
        this.data1 = data1;
    }

    public RESP(Object data , Object data1 , Object data2) {
        this.data = data;
        this.data1 = data1;
        this.data2 = data2;
    }
    public RESP(Object data , Object data1 , Object data2 , Object data3 , Object data4) {
        this.data = data;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
    }

    public static RESP ok(Object data , Object data1 , Object data2) {
        return new RESP(data , data1 , data2);
    }

    public static RESP ok(Object data , Object data1 , Object data2 , Object data3) {
        return new RESP(data , data1 , data2 , data3);
    }
    //5个参数
    public static RESP ok(Object data , Object data1 , Object data2 , Object data3 , Object data4) {
        return new RESP(data , data1 , data2 , data3 , data4);
    }

    public static RESP ok(Object data , int pageNum , int total) {
        return new RESP(data , pageNum , total);
    }

    public static RESP ok(Object data , Object data1) {
        return new RESP(data , data1);
    }

    public static RESP ok(Object data) {
        return new RESP(data);
    }
}

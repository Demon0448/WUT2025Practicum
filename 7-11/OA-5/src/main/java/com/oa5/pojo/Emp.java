package com.oa5.pojo;

import lombok.Data;

import java.io.Serializable;


@Data
public class Emp implements Serializable {
    private int number;
    private String name;
    private String pwd;
    private String birthday;
    private String address;
    private int  dept_id;
    private int duty_id;
    private String dept_name;
    private String duty_name;

}

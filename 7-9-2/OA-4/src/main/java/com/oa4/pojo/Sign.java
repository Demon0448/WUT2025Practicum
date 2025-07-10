package com.oa4.pojo;

import lombok.Data;

import java.io.Serializable;


@Data
public class Sign implements Serializable {
    private int id;
    private String signDate;
    private int number;
    private String state;
    private String dept_name;
    private String name;
    private String type;
    private String sign_address;
    private int tag;
}

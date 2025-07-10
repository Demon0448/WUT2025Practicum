package com.oa4.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Duty implements Serializable {
    private int duty_id;
    private String duty_name;
    private int duty_num;
}

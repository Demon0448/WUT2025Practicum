package com.oa5.pojo;

import lombok.Data;

import java.io.Serializable;


@Data
public class Department implements Serializable {
    private int dept_id;
    private String dept_name;
    private int dept_num;
}

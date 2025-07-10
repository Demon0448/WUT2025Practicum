package com.oa4.pojo;

import lombok.Data;

import java.io.Serializable;


@Data
public class Admin implements Serializable {
    private int id;
    private String name;
    private String pwd;
}

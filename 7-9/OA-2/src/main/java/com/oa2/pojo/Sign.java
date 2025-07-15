package com.oa2.pojo;

import lombok.Data;

@Data
public class Sign {
    private String id;
    
    private String signDate;
    
    private int number;
    
    private String state;
    
    private String dept_name;
    
    private String name;
    
    private String type;
    
    private String sign_address;
    
    private int tag;
    
    // 添加时间戳字段，用于排序和统计
    private Long timestamp;
    
    // 添加日期字段，用于日期范围查询
    private String dateOnly;
}

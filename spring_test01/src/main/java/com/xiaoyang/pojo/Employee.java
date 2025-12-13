package com.xiaoyang.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("emp_info")
public class Employee {
    @TableId
    private Integer id;

    @TableField("emp_name")
    private String name;
    private String address;

    @TableField("emp_age")
    private int age;

    private String password;
    private String phoneNumber;

    @TableField("emp_mail")
    private String email;
    @TableField("emp_be")
    private boolean isActive;
    @TableField("entrytime")
    private LocalDate entryTime;

    private LocalDate birth;
}

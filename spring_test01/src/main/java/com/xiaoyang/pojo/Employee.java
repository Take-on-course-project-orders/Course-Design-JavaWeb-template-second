package com.xiaoyang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;

@TableName("emp_info")
public class Employee {
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    @TableField("emp_name")
    private String name;
    
    private String address;
    
    @TableField("emp_age")
    private Integer age;
    
    private String password;
    
    @TableField("phone_number")
    private String phoneNumber;
    
    @TableField("emp_mail")
    private String email;
    
    @TableField("emp_be")
    private boolean isActive = true;
    
    @TableField("entrytime")
    private LocalDate entryTime;
    
    private LocalDate birth;

    public Employee() {
    }

    public Employee(Integer id, String name, String address, Integer age, String password, String phoneNumber, String email, boolean isActive, LocalDate entryTime, LocalDate birth) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isActive = isActive;
        this.entryTime = entryTime;
        this.birth = birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDate getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDate entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
}

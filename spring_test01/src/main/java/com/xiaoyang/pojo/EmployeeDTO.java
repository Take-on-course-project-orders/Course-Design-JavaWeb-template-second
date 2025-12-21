package com.xiaoyang.pojo;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class EmployeeDTO {
    private Integer id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "地址不能为空")
    private String address;

    @Min(value = 0, message = "年龄不能为负数")
    @Max(value = 150, message = "年龄不能大于150")
    private int age;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须为6-20位")
    private String password;

    @NotBlank(message = "电话号码不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "电话号码格式不正确")
    private String phoneNumber;

    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    private boolean isActive;

    @NotNull(message = "入职日期不能为空")
    private LocalDate entryTime;

    @NotNull(message = "出生日期不能为空")
    private LocalDate birth;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Integer id, String name, String address, int age, String password, String phoneNumber, String email, boolean isActive, LocalDate entryTime, LocalDate birth) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
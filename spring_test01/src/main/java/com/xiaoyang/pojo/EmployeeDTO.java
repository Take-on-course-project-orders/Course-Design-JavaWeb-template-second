package com.xiaoyang.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("emp_info")
public class EmployeeDTO {
    @TableId
    private Integer id;

    @TableField("name")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @TableField("addr")
    @NotBlank(message = "地址不能为空")
    private String address;

    @TableField("age")
    @Min(value = 0, message = "年龄不能为负数")
    @Max(value = 150, message = "年龄不能大于150")
    private int age;

    @TableField("password")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须为6-20位")
    private String password;

    @TableField("emp_number")
    @NotBlank(message = "电话号码不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "电话号码格式不正确")
    private String phoneNumber;

    @TableField("emp_mail")
    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    private boolean isActive;
    @TableField("entry_time")
    @NotNull(message = "入职日期不能为空")
    private LocalDate entryTime;

    @NotNull(message = "出生日期不能为空")
    private LocalDate birth;
}
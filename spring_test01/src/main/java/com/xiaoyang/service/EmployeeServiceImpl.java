package com.xiaoyang.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyang.mapper.EmployeeMapper;
import com.xiaoyang.pojo.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService  {

}

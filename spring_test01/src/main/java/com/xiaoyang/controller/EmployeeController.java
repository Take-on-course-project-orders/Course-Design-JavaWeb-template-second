package com.xiaoyang.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoyang.common.Result;
import com.xiaoyang.common.ResultCode;
import com.xiaoyang.pojo.Employee;
import com.xiaoyang.pojo.EmployeeDTO;
import com.xiaoyang.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/emp")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public Result<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.list();
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), employees);
    }

    @GetMapping("/page")
    public Result<Page<Employee>> getEmpPage(@RequestParam(defaultValue = "1") Integer pageNum, 
                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                             @RequestParam(required = false) String keyword) {
        Page<Employee> page = new Page<>(pageNum, pageSize);
        Page<Employee> employees = employeeService.lambdaQuery()
                .like(keyword != null && !keyword.isEmpty(), Employee::getName, keyword)
                .or(keyword != null && !keyword.isEmpty(), queryWrapper -> queryWrapper.like(Employee::getEmail, keyword))
                .or(keyword != null && !keyword.isEmpty(), queryWrapper -> queryWrapper.like(Employee::getPhoneNumber, keyword))
                .page(page);
                
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), employees);
    }


    @GetMapping("/find/{id}")
    public Result<Employee> getEmpById(@PathVariable Integer id) {
        Employee employee = employeeService.getById(id);
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), employee);
    }


    @PostMapping("/create")
    public Result<Boolean> addEmp(@Valid @RequestBody EmployeeDTO employeeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return new Result<>(ResultCode.BAD_REQUEST.getCode(), msg, false);
        }

        try {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDTO, employee);
            System.out.println("Employee to save: " + employee);
            boolean isSuccess = employeeService.save(employee);
            return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), isSuccess);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResultCode.FAILURE.getCode(), "创建员工失败: " + e.getMessage(), false);
        }
    }


    @PutMapping("/update")
    public Result<Boolean> updateEmp(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        boolean isOk = employeeService.updateById(employee);
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), isOk);
    }

    @DeleteMapping("/delete/{id}")
    public Result<Boolean> deleteEmp(@PathVariable Integer id) {
        boolean isOK = employeeService.removeById(id);
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), isOK);
    }
}

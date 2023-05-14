package com.example.collection_list.controller;

import com.example.collection_list.model.Employee;
import com.example.collection_list.service.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentServiceImpl departmentServiceImpl;

    public DepartmentController(DepartmentServiceImpl departmentServiceImpl) {
        this.departmentServiceImpl = departmentServiceImpl;
    }


    @GetMapping("/max-salary")
    public Employee max(@RequestParam int departmentId) {
        return departmentServiceImpl.max(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee min(@RequestParam int departmentId) {
        return departmentServiceImpl.min(departmentId);
    }

    @GetMapping(value = "/all", params = "departmentId")
    public Collection<Employee> allDept(@RequestParam int departmentId) {
        return departmentServiceImpl.allDept(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> all() {
        return departmentServiceImpl.all();
    }

}

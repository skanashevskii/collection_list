package com.example.collection_list.controller;

import com.example.collection_list.model.Employee;
import com.example.collection_list.service.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.*;


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


    @GetMapping("/{departmentId}/salary/max")
    public Employee max(@PathVariable int departmentId) {
        return departmentServiceImpl.max(departmentId);
    }

    @GetMapping("/{departmentId}/salary/min")
    public Employee min(@PathVariable int departmentId) {
        return departmentServiceImpl.min(departmentId);
    }
    @GetMapping("/{departmentId}/salary/sum")
    public int sum(@PathVariable int departmentId) {
        return departmentServiceImpl.sumSalaryDept(departmentId);
    }


    @GetMapping( "/{departmentId}/employees")
    public Collection<Employee> allDept(@PathVariable int departmentId) {
        return departmentServiceImpl.allDept(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> all() {
        return departmentServiceImpl.all();
    }

}

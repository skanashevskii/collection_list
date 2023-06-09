package com.example.collection_list.controller;

import com.example.collection_list.model.Employee;
import com.example.collection_list.service.EmployeeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RequestMapping("/employee")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String family,
                                @RequestParam String name,
                                @RequestParam int salary,
                                @RequestParam int department) {
        return employeeService.addEmployee(family, name, salary, department);
    }

    @GetMapping("/delete")
    public Employee deleteEmployee(@RequestParam String family, @RequestParam String name) {
        return employeeService.deleteEmployee(family, name);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String family, @RequestParam String name) {
        return employeeService.findEmployee(family, name);
    }

    @GetMapping
    public Collection<Employee> findAll() {
        return employeeService.findAll();
    }
}



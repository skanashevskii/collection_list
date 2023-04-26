package com.example.collection_list.controller;

import com.example.collection_list.exceptions.EmployeeAlreadyAddedException;
import com.example.collection_list.exceptions.EmployeeNotFoundException;
import com.example.collection_list.exceptions.EmployeeStorageIsFullException;
import com.example.collection_list.service.Employee;
import com.example.collection_list.service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/employee")
@RestController
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String family, @RequestParam String name) {
        try {
            return employeeService.addEmployee(family, name);
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

        @GetMapping("/delete")
        public Employee deleteEmployee (@RequestParam String family, @RequestParam String name){
            try {
                return employeeService.deleteEmployee(family, name);
            }catch (EmployeeNotFoundException e){
                throw new RuntimeException(e.getMessage());
            }
        }

        @GetMapping("/find")
        public Employee findEmployee (@RequestParam String family, @RequestParam String name){
            try {
                return employeeService.findEmployee(family, name);
            }catch (EmployeeNotFoundException e){
                throw new RuntimeException(e.getMessage());
            }
        }

        @GetMapping("/getList")
        public String getAllList () {
            return employeeService.getEmployeeList().toString();
        }
    }



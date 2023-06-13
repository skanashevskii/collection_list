package com.example.collection_list.service;

import com.example.collection_list.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee max(int dept) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> employee.getDepartment() == dept)
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public Employee min(int dept) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> employee.getDepartment() == dept)
                .min(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }
    @Override
    public int sumSalaryDept(int dept) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> employee.getDepartment() == dept)
                .mapToInt(Employee::getSalary).sum();
    }

    @Override
    public Collection<Employee> allDept(int dept) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> employee.getDepartment() == dept)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> all() {
        return employeeService.findAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

    }
}

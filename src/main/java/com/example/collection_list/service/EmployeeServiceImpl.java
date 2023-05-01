package com.example.collection_list.service;

import com.example.collection_list.exceptions.EmployeeAlreadyAddedException;
import com.example.collection_list.exceptions.EmployeeNotFoundException;
import com.example.collection_list.exceptions.EmployeeStorageIsFullException;
import com.example.collection_list.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String,Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String family, String name) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        Employee employee = new Employee(family, name);

        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник " + employee + " уже существует");
        }
        employees.put(employee.getFullName(),employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String family, String name) throws EmployeeNotFoundException {
        Employee employee = new Employee(family, name);
        if (employees.containsKey(employee.getFullName())) {

            return employees.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Сотрудник " + family + " " + name + " не найден");
    }

    @Override
    public Employee findEmployee(String family, String name) throws EmployeeNotFoundException {
        Employee employee = new Employee(family, name);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Сотрудник " + family + " " + name + " " + " не найден");
    }

    @Override
    public Collection<Employee> findAll() {
        return  Collections.unmodifiableCollection(employees.values());//для безопасности передаем копию
    }
}


package com.example.collection_list.service;

import com.example.collection_list.exceptions.EmployeeAlreadyAddedException;
import com.example.collection_list.exceptions.EmployeeNotFoundException;
import com.example.collection_list.exceptions.EmployeeStorageIsFullException;
import com.example.collection_list.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public final static int sizeArray = 3;
    private final List<Employee> employeeList;

    public EmployeeServiceImpl() {
        this.employeeList = new ArrayList<>(sizeArray);
    }

    @Override
    public Employee addEmployee(String family, String name) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        Employee employee = new Employee(family, name);
        if (employeeList.size() >= sizeArray) {
            throw new EmployeeStorageIsFullException(" Массив переполнен " + employeeList.size());
        }
        if (employeeList.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник " + employee + " уже существует");
        }
        employeeList.add(employee);
        return employee;
    }

    @Override
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    @Override
    public Employee deleteEmployee(String family, String name) throws EmployeeNotFoundException {
        Employee employee = new Employee(family,name);
            if (employeeList.contains(employee)) {
                employeeList.remove(employee);
                return employee;
            }
        throw new EmployeeNotFoundException("Сотрудник " + family + " " + name + " не найден");
    }

    @Override
    public Employee findEmployee(String family, String name) throws EmployeeNotFoundException {
        Employee employee = new Employee(family,name);
            if (employeeList.contains(employee)) {
                return employee;
        }
        throw new EmployeeNotFoundException("Сотрудник " + family + " " + name + " " + " не найден");
    }

    @Override
    public List<Employee> findAll() {
        return Collections.unmodifiableList(employeeList);//для безопасности передаем копию
    }
}


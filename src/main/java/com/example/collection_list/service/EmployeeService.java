package com.example.collection_list.service;

import com.example.collection_list.exceptions.EmployeeAlreadyAddedException;
import com.example.collection_list.exceptions.EmployeeNotFoundException;
import com.example.collection_list.exceptions.EmployeeStorageIsFullException;
import com.example.collection_list.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface EmployeeService {

    Employee addEmployee(String family, String name) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException;

    Employee deleteEmployee(String family, String name) throws EmployeeNotFoundException;

    Employee findEmployee(String family, String name) throws EmployeeNotFoundException;

    Collection<Employee> findAll();
}

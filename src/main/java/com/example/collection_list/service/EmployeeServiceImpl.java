package com.example.collection_list.service;

import com.example.collection_list.exceptions.EmployeeAlreadyAddedException;
import com.example.collection_list.exceptions.EmployeeNotFoundException;
import com.example.collection_list.exceptions.EmployeeStorageIsFullException;
import com.example.collection_list.exceptions.InvalidInputException;
import com.example.collection_list.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public static int sizeArray = 10;
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>(sizeArray);
    }

    @Override
    public Employee addEmployee(String family, String name, int salary, int department)
            throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        validateInput(family, name);
        Employee employee = new Employee(family, name, salary, department);
        if (employees.size() >= sizeArray) {
            throw new EmployeeStorageIsFullException(" Массив переполнен " + employees.size());
        }
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник " + employee + " уже существует");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String family, String name) throws EmployeeNotFoundException {
        validateInput(family, name);
        String key = getKey(family, name).toLowerCase();
        if (employees.containsKey(key)) {
            return employees.remove(key);

        }
        throw new EmployeeNotFoundException("Сотрудник " + family + " " + name + " не найден");
    }

    @Override
    public Employee findEmployee(String family, String name) throws EmployeeNotFoundException {
        validateInput(family, name);
        String key = getKey(family, name).toLowerCase();
        if (employees.containsKey(key)) {
            return employees.get(key);
        }
        throw new EmployeeNotFoundException("Сотрудник " + family + " " + name + " " + " не найден");
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());//для безопасности передаем копию
    }

    public String getKey(String family, String name) {
        return family + " " + name;
    }

    private void validateInput(String family, String name) {
        if (!(StringUtils.isAlpha(family) && StringUtils.isAlpha(name))) {
            throw new InvalidInputException();
        }
    }

    //2 й вариант
    private void checkArg(String... args) {
        for (String arg : args) {
            if (!StringUtils.isAlpha(arg)) {
                throw new InvalidInputException();
            }
        }
    }
}


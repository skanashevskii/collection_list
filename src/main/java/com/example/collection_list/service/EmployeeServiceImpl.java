package com.example.collection_list.service;

import com.example.collection_list.exceptions.EmployeeAlreadyAddedException;
import com.example.collection_list.exceptions.EmployeeNotFoundException;
import com.example.collection_list.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl {
    public final static int sizeArray=3;
    private final List<Employee> employeeList;

    public EmployeeServiceImpl(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Employee addEmployee(String family, String name) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        Employee employee = new Employee(family,name);
            if(employeeList.size()>=sizeArray){
                throw new EmployeeStorageIsFullException(" Массив переполнен "+employeeList.size());
            }
           if(employeeList.contains(employee)) {
               throw new EmployeeAlreadyAddedException("Сотрудник " +employee+ " уже существует");
           }
           employeeList.add(employee);
           return employee;
    }
    public List<Employee> getEmployeeList(){
        return employeeList;
    }


    public Employee deleteEmployee(String family, String name) throws EmployeeNotFoundException {
        for (Employee employee : employeeList) {
            if (employee.getFamily().equals(family) && employee.getName().equals(name)) {
                employeeList.remove(employee);
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник " + family + " " + name + " не найден");
    }

    public Employee findEmployee(String family, String name) throws EmployeeNotFoundException {

        for (Employee employee : employeeList) {
            if (employee.getFamily().equals(family) && employee.getName().equals(name)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник " + family + " " + name + " " + " не найден");
    }
}


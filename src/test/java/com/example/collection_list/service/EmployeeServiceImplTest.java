package com.example.collection_list.service;

import com.example.collection_list.exceptions.EmployeeAlreadyAddedException;
import com.example.collection_list.exceptions.EmployeeNotFoundException;
import com.example.collection_list.exceptions.EmployeeStorageIsFullException;
import com.example.collection_list.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;


class EmployeeServiceImplTest {
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setup() {

        employeeService = new EmployeeServiceImpl();
    }

    @Test
    public void addEmployee() throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        String family = "Ivanov";
        String name = "Ivan";
        int salary = 10000;
        int department = 1;

        Employee addedEmployee = employeeService.addEmployee(family, name, salary, department);

        Assertions.assertEquals(family + " " + name, addedEmployee.getFullName());
        Assertions.assertEquals(salary, addedEmployee.getSalary());
        Assertions.assertEquals(department, addedEmployee.getDepartment());
    }

    @Test
    public void testAddEmployee_ThrowsEmployeeAlreadyAddedException() throws EmployeeStorageIsFullException {
        String family = "Ivanov";
        String name = "Ivan";
        int salary = 10000;
        int department = 1;

        try {
            employeeService.addEmployee(family, name, salary, department);
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
            e.printStackTrace();
        }
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () ->
                employeeService.addEmployee(family, name, salary, department)
        );
    }

    @Test
    public void testAddEmployee_ThrowsEmployeeStorageIsFullException() throws EmployeeAlreadyAddedException {
        String family = "Ivanov";
        String name = "Ivan";
        int salary = 10000;
        int department = 1;

        EmployeeServiceImpl.sizeArray = 1;

        try {
            employeeService.addEmployee("Ivanov", "Ivan", 1000, 2);
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
            e.printStackTrace();
        }
        Assertions.assertThrows(EmployeeStorageIsFullException.class, () ->
                employeeService.addEmployee(family, name, salary, department)
        );
    }

    @Test
    public void testDeleteEmployee() throws EmployeeNotFoundException {

        String family = "Ivanov";
        String name = "Ivan";
        int salary = 5000;
        int department = 1;


        try {
            employeeService.addEmployee(family, name, salary, department);
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
            e.printStackTrace();
        }

        Employee deletedEmployee = employeeService.deleteEmployee(family, name);

        Assertions.assertEquals(family + " " + name, deletedEmployee.getFullName());
        //Assertions.assertFalse(employeeService.getEmployees().containsKey(deletedEmployee.getFullName().toLowerCase()));
    }

    @Test
    public void testDeleteEmployee_ThrowsEmployeeNotFoundException() {

        String family = "Ivanov";
        String name = "Ivan";


        Assertions.assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.deleteEmployee(family, name)
        );
    }

    @Test
    public void testFindEmployee() throws EmployeeNotFoundException {

        String family = "Ivanov";
        String name = "Ivan";
        int salary = 5000;
        int department = 1;

        try {
            employeeService.addEmployee(family, name, salary, department);
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
            e.printStackTrace();
        }

        Employee foundEmployee = employeeService.findEmployee(family, name);

        Assertions.assertEquals(family + " " + name, foundEmployee.getFullName());
    }

    @Test
    public void testFindEmployee_ThrowsEmployeeNotFoundException() {

        String family = "Ivanov";
        String name = "Ivan";


        Assertions.assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.findEmployee(family, name)
        );
    }

    @Test
    public void testFindAll_Success() {

        String family1 = "Ivanov";
        String name1 = "Ivan";
        int salary1 = 5000;
        int department1 = 1;

        String family2 = "Petrov";
        String name2 = "Petr";
        int salary2 = 6000;
        int department2 = 2;

        // Добавляем сотрудников
        try {
            employeeService.addEmployee(family1, name1, salary1, department1);
            employeeService.addEmployee(family2, name2, salary2, department2);
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
            e.printStackTrace();
        }

        Collection<Employee> allEmployees = employeeService.findAll();

        Assertions.assertEquals(2, allEmployees.size());
        for (Employee employee : allEmployees) {
            Assertions.assertTrue(employee.getFullName().equals(family1 + " " + name1)
                    || employee.getFullName().equals(family2 + " " + name2));
        }
    }
}


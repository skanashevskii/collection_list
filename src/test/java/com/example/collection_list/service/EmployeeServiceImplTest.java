package com.example.collection_list.service;

import com.example.collection_list.exceptions.EmployeeAlreadyAddedException;
import com.example.collection_list.exceptions.EmployeeNotFoundException;
import com.example.collection_list.exceptions.EmployeeStorageIsFullException;
import com.example.collection_list.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class EmployeeServiceImplTest {
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setup() {

        employeeService = new EmployeeServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("parametrs")
    public void addEmployee(String family, String name, int salary, int department)
            throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {

        Employee addedEmployee = employeeService.addEmployee(family, name, salary, department);

        /**  Импорт статик(import static org.junit.jupiter.api.Assertions.assertEquals;)
         метода позволяет сократить код Assertions.assertEquals -> assertEquals*/

        assertEquals((family + " " + name).toLowerCase(), addedEmployee.getFullName());
        assertEquals(salary, addedEmployee.getSalary());
        assertEquals(department, addedEmployee.getDepartment());
    }

    @ParameterizedTest
    @MethodSource("parametrs")
    public void testAddEmployee_ThrowsEmployeeAlreadyAddedException(String family, String name, int salary, int department)
            throws EmployeeStorageIsFullException {

        try {
            employeeService.addEmployee(family, name, salary, department);
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
            e.printStackTrace();
        }
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () ->
                employeeService.addEmployee(family, name, salary, department)
        );
    }

    @ParameterizedTest
    @MethodSource("parametrs")
    public void testAddEmployee_ThrowsEmployeeStorageIsFullException(String family, String name, int salary, int department)
            throws EmployeeAlreadyAddedException {
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

    @ParameterizedTest
    @MethodSource("parametrs")
    public void testDeleteEmployee(String family, String name, int salary, int department)
            throws EmployeeNotFoundException {

        try {
            employeeService.addEmployee(family, name, salary, department);
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
            e.printStackTrace();
        }
        Employee deletedEmployee = employeeService.deleteEmployee(family, name);

        assertEquals((family + " " + name).toLowerCase(), deletedEmployee.getFullName());
        assertFalse(employeeService.findAll().contains(deletedEmployee.getFullName()));
    }

    @Test
    public void testDeleteEmployee_ThrowsEmployeeNotFoundException() {

        String family = "Ivanov";
        String name = "Ivan";


        Assertions.assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.deleteEmployee(family, name)
        );
    }

    @ParameterizedTest
    @MethodSource("parametrs")
    public void testFindEmployee(String family, String name, int salary, int department)
            throws EmployeeNotFoundException {
        try {
            employeeService.addEmployee(family, name, salary, department);
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
            e.printStackTrace();
        }

        Employee foundEmployee = employeeService.findEmployee(family, name);

        assertEquals((family + " " + name).toLowerCase(), foundEmployee.getFullName());
    }

    @Test
    public void testFindEmployee_ThrowsEmployeeNotFoundException() {

        String family = "Ivanov";
        String name = "Ivan";


        Assertions.assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.findEmployee(family, name)
        );
    }

    @ParameterizedTest
    @MethodSource("parametrs")
    public void testFindAll_Success(String family1, String name1, int salary1, int department1) {

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

        assertEquals(2, allEmployees.size());
        for (Employee employee : allEmployees) {
            assertTrue(employee.getFullName().equals((family1 + " " + name1).toLowerCase())
                    || employee.getFullName().equals((family2 + " " + name2).toLowerCase()));
        }
    }

    private static Collection<Arguments> parametrs() {
        return List.of(
                Arguments.of("Ivanov", "Ivan", 5000, 1)

        );
    }
}


package com.example.collection_list.service;

import com.example.collection_list.model.Employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;




@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    private DepartmentServiceImpl departmentService;
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = mock(EmployeeServiceImpl.class);
        departmentService = new DepartmentServiceImpl(employeeService);

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Ivanov", "Ivan", 9000, 1));
        employees.add(new Employee("Petrov", "Petr", 5000, 4));
        employees.add(new Employee("Goncharov", "Gonchar", 5000, 1));
        employees.add(new Employee("Ivanov", "Ivan", 10000, 1));
        Mockito.when(employeeService.findAll()).thenReturn(employees);

    }

    @Test
    public void max() {
        Employee result = departmentService.max(1);
        assertEquals("Ivan", result.getName());
        assertEquals("Ivanov", result.getFamily());
        assertEquals(10000, result.getSalary());
        assertEquals(1, result.getDepartment());
        verify(employeeService, times(1)).findAll();
    }

    @Test
    public void min() {
        Employee result = departmentService.min(1);
        assertEquals("Gonchar", result.getName());
        assertEquals("Goncharov", result.getFamily());
        assertEquals(5000, result.getSalary());
        assertEquals(1, result.getDepartment());
        verify(employeeService, times(1)).findAll();
    }

    @Test
    public void sumSalaryDept() {
        assertEquals(24000, departmentService.sumSalaryDept(1));
        verify(employeeService, times(1)).findAll();
    }

    @Test
    public void allDept() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Ivanov", "Ivan", 9000, 1));
        employees.add(new Employee("Petrov", "Petr", 5000, 4));
        employees.add(new Employee("Goncharov", "Gonchar", 5000, 1));
        employees.add(new Employee("Ivanov", "Ivan", 10000, 1));

        Collection<Employee> result = departmentService.allDept(1);
        List<Employee> expected = employees.stream()
                .filter(employee -> employee.getDepartment() == 1)
                .toList();
        Assertions.assertEquals(expected.size(), result.size());
        Assertions.assertTrue(result.containsAll(expected));

        verify(employeeService,times(1)).findAll();


    }

    @Test
    public void all() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Ivanov", "Ivan", 9000, 1));
        employees.add(new Employee("Petrov", "Petr", 5000, 4));
        employees.add(new Employee("Goncharov", "Gonchar", 5000, 1));
        employees.add(new Employee("Ivanov", "Ivan", 10000, 1));

       //Mockito.when(employeeService.findAll()).thenReturn(employees); -перенесен в setup()

        Map<Integer, List<Employee>> result = departmentService.all();

        Map<Integer, List<Employee>> expected = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        assertEquals(expected.size(), result.size());
        for (int department : expected.keySet()) {
            assertTrue(result.containsKey(department));
            List<Employee> expectedEmployees = expected.get(department);
            List<Employee> resultEmployees = result.get(department);
            assertEquals(expectedEmployees.size(), resultEmployees.size());
            Assertions.assertTrue(resultEmployees.containsAll(expectedEmployees));
        }

        verify(employeeService,times(1)).findAll();

    }
}
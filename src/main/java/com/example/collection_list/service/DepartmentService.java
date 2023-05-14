package com.example.collection_list.service;

import com.example.collection_list.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee max(int dept);

    Employee min(int dept);

    Collection<Employee> allDept(int dept);

    Map<Integer, List<Employee>> all();
}

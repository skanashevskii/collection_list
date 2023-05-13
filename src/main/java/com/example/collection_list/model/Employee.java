package com.example.collection_list.model;

import java.util.Objects;

public class Employee {
    private final String family;
    private final String name;
    private final int salary;
    private final int department;

    public Employee(String family, String name, int salary, int department) {
        this.family = family;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

/*
    @Override
    public String toString() {
        return " Фамилия: "
                + this.family + " Имя: " + this.name;

    }
*/

    @Override
    public String toString() {
        return "Employee{" +
                "family='" + family + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }

    public String getFamily() {
        return this.family;
    }

    public String getName() {
        return this.name;
    }

    public String getFullName() {
        return (this.family + " " + this.name).toLowerCase();
    }

    public int getSalary() {
        return salary;
    }

    public int getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary && department == employee.department && Objects.equals(family, employee.family) && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(family, name, salary, department);
    }
}




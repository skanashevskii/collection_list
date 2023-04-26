package com.example.collection_list.model;

import java.util.Objects;

public class Employee {
    private final String family;
    private final String name;

    public Employee(String family, String name) {
        this.family = family;
        this.name = name;
    }

    @Override
    public String toString() {
        return " Фамилия: "
                + this.family + " Имя: " + this.name;

    }


    public String getFamily() {
        return this.family;
    }

    public String getName() {
        return this.name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return family.equals(employee.family) && name.equals(employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(family, name);
    }

}




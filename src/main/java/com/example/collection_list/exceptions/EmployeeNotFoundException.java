package com.example.collection_list.exceptions;

public class EmployeeNotFoundException extends Throwable{
    public EmployeeNotFoundException(String message){
        super(message);
    }
}

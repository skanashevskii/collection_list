package com.example.collection_list.exceptions;

public class EmployeeStorageIsFullException extends Throwable {
    public EmployeeStorageIsFullException(String message){
        super(message);
    }
}

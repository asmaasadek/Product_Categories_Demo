package com.assignment.demo.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(int id) {
        super("Category Id not found : " + id);
    }
}

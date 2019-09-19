package com.assignment.demo.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id) {
        super("Product Id not found : " + id);
    }
}

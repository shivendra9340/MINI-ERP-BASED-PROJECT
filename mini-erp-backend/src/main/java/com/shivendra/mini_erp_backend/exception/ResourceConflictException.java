package com.shivendra.mini_erp_backend.exception;


public class ResourceConflictException extends RuntimeException {

    public ResourceConflictException(String message) {
        super(message);
    }
}
package com.example.property.exception;

public class FileAlreadyExistException extends Exception {
    public FileAlreadyExistException(String message) {
        super(message);
    }
}

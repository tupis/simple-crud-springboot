package com.tupi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BooksNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public BooksNotFoundException(String message) {
        super(message);
    }

    public BooksNotFoundException() {
        super("No records found for this ID!");
    }

}


package com.tupi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BooksNotNullException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public BooksNotNullException(String message) {
        super(message);
    }

    public BooksNotNullException() {
        super("Books object does not exist. Please create a person object first.");
    }

}


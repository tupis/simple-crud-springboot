package com.tupi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PersonNotNullException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public PersonNotNullException(String message) {
        super(message);
    }

    public PersonNotNullException() {
        super("Person object does not exist. Please create a person object first.");
    }

}


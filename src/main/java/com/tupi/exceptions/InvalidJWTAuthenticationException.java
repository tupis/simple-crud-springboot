package com.tupi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;
import java.io.Serial;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJWTAuthenticationException extends AuthenticationException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidJWTAuthenticationException(String ex) {
        super(ex);
    }

    public InvalidJWTAuthenticationException() {
        super("User does not have permission");
    };

}

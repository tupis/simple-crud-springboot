package com.tupi.controllers;

import com.tupi.data.vo.v1.security.AccountCredentialsVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

public interface AuthController {
    ResponseEntity<?> signIn(AccountCredentialsVO data);

    ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String refreshToken);
}

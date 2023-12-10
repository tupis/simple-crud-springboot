package com.tupi.controllers;

import com.tupi.data.vo.v1.security.AccountCredentialsVO;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    ResponseEntity<?> signIn(AccountCredentialsVO data);
}

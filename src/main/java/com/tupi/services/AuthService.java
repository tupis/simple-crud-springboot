package com.tupi.services;

import com.tupi.data.vo.v1.security.AccountCredentialsVO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> signIn(AccountCredentialsVO data);

    ResponseEntity<?> refreshToken(String refreshToken);

}

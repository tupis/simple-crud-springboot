package com.tupi.controllers.impl;

import com.tupi.controllers.AuthController;
import com.tupi.data.vo.v1.security.AccountCredentialsVO;
import com.tupi.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.logging.Logger;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthControllerImpl implements AuthController {
    private final Logger logger = Logger.getLogger(AuthControllerImpl.class.getName());

    @Autowired
    private AuthService auth;

    @Operation(summary = "Authenticates a user and returns a token")
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody AccountCredentialsVO data) {

        if(!validateSignInBody(data)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials");

        var token = auth.signIn(data);

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @Operation(summary = "Create new access token from refresh token")
    @PutMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        var token = validateRefreshToken(refreshToken);
        if(token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid token");

        var result = this.auth.refreshToken(token);
        return ResponseEntity.status(200).body(result);
    }


    private Boolean validateSignInBody(AccountCredentialsVO data) {
        if(data == null) {
            return false;
        }

        if(data.getUsername() == null) {
            return false;
        }

        if(data.getUsername().isEmpty()) {
            return false;
        }

        if(data.getUsername().isBlank()) {
            return false;
        }

        if(data.getPassword() == null) {
            return false;
        }

        if(data.getPassword().isEmpty()) {
            return false;
        }

        if(data.getPassword().isBlank()) {
            return false;
        }

        return true;
    }

    private String validateRefreshToken(String token) {
        if(token.isBlank()) {
            return null;
        }

        if(token.split("Bearer ")[1] == null || Objects.equals(token.split("Bearer ")[1], ""))  {
            return null;
        }

        return token.split("Bearer ")[1].strip();
    }
}

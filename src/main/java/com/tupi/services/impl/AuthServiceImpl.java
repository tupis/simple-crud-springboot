package com.tupi.services.impl;

import com.tupi.data.vo.v1.security.AccountCredentialsVO;
import com.tupi.data.vo.v1.security.TokenVO;
import com.tupi.models.User;
import com.tupi.repositories.UserRepository;
import com.tupi.security.jwt.JWTTokenProvider;
import com.tupi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;

    public ResponseEntity<TokenVO> signIn(AccountCredentialsVO data) {
        try {
            var username = data.getUsername();
            var password = data.getPassword();

            var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            List<User> users = repository.findAll();

            User user = repository.findUsersByUserName(username);

            if (user == null) {
                System.out.println("User is null");
                throw new UsernameNotFoundException("Username not found!");
            }

            var tokenResponse = jwtTokenProvider.createAccessToken(username, user.getRoles().toArray(new String[0]));

            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }

    }
}

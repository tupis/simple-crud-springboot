package com.tupi.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Service
public class JWTTokenFilter extends GenericFilterBean {

    @Autowired
    private JWTTokenProvider provider;

    public JWTTokenFilter(JWTTokenProvider provider) {
        this.provider = provider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = provider.resolveToken((HttpServletRequest) request);

        if(token != null && provider.validateToken(token)) {
            Authentication auth = provider.getAuthentication(token);
            if(auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        chain.doFilter(request, response);

    }
}

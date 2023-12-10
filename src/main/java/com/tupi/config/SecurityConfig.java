package com.tupi.config;

import com.tupi.security.jwt.JWTConfigurer;
import com.tupi.security.jwt.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
//@Profile({"dev", "test"})
public class SecurityConfig {

    private final String [] WHITE_LIST = {
            "/swagger-ui/**",
            "/api-docs/**",
            "/auth/signIn",
    };

    @Autowired
    private JWTTokenProvider provider;

    public SecurityConfig(JWTTokenProvider provider) {
        this.provider = provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authHttpReq -> authHttpReq
                        .requestMatchers(WHITE_LIST).permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers("/users").denyAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .apply(new JWTConfigurer(provider));

        return http.build();
    }


}

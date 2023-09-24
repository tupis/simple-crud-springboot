package com.tupi.javacourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan("com.tupi.models")
@SpringBootApplication
@EnableJpaRepositories("com.tupi.repositories")
@ComponentScan("com.tupi")
public class Startup {
    public static void main(String[] args) {
        SpringApplication.run(Startup.class, args);
    }
}

package com.example.technicaltask;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class TechnicalTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechnicalTaskApplication.class, args);
    }

}

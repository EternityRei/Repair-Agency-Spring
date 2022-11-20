package com.example.repairagencyspring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = {"com.example.repairagencyspring.service",
        "com.example.repairagencyspring.repository", "org.springframework.security.crypto.bcrypt"})
@EnableJpaRepositories("com.example.repairagencyspring.repository")
@EnableWebMvc
@Slf4j
public class RepairAgencySpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepairAgencySpringApplication.class, args);
        //BasicConfigurator.configure();
    }

}

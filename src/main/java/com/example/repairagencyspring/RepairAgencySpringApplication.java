package com.example.repairagencyspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.repairagencyspring.service",
        "com.example.repairagencyspring.repository", "org.springframework.security.crypto.bcrypt"})
@EnableJpaRepositories("com.example.repairagencyspring.repository")
public class RepairAgencySpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepairAgencySpringApplication.class, args);
        //BasicConfigurator.configure();
    }

}

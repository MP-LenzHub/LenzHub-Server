package com.example.renzhubserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RenzHubServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RenzHubServerApplication.class, args);
    }

}

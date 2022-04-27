package com.example.database_user;

import com.example.database_user.services.MeilisearchService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatabaseUserApplication {

    public static void main(String[] args) {

        MeilisearchService.getInstance();
        SpringApplication.run(DatabaseUserApplication.class, args);
    }

}

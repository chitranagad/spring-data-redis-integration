package com.natwest.springdataredisintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class SpringDataRedisIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataRedisIntegrationApplication.class, args);
    }

}

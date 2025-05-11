package com.jaimevalencia.courier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.jaimevalencia.courier.model"})
@EnableJpaRepositories(basePackages = {"com.jaimevalencia.courier.repository"})
public class CourierApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourierApplication.class, args);
        
    }
}
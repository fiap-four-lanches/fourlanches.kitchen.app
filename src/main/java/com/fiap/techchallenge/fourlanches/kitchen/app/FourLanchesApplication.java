package com.fiap.techchallenge.fourlanches.kitchen.app;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class FourLanchesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FourLanchesApplication.class, args);
    }

}

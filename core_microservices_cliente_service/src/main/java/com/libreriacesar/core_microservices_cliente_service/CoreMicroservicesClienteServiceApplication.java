package com.libreriacesar.core_microservices_cliente_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@FeignClient
public class CoreMicroservicesClienteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreMicroservicesClienteServiceApplication.class, args);
    }

}

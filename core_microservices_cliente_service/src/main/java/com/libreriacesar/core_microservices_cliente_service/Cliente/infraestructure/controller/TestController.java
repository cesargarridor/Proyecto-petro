package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.ListTablesRequest;

@RestController
public class TestController {

    private final AmazonDynamoDB amazonDynamoDB;

    public TestController(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    @GetMapping("/test")
    public String testDynamoDB() {
        try {
            // Intenta listar las tablas para verificar la conexión
            amazonDynamoDB.listTables(new ListTablesRequest());
            return "Conexión exitosa a DynamoDB!";
        } catch (Exception e) {
            return "Error al conectar a DynamoDB: " + e.getMessage();
        }
    }
}


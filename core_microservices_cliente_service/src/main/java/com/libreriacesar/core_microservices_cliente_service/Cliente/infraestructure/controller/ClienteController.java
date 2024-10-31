package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    @PostMapping("/crearCliente")
    public String crearCliente() {

        return "Cliente creado";
    }


}

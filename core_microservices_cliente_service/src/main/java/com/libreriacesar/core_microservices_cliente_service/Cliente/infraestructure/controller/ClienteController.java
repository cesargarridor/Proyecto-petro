package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller;

import com.libreriacesar.core_microservices_cliente_service.Cliente.application.port.ClientUseCase;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClientUseCase clienteUseCase;

    @Autowired
    public ClienteController(ClientUseCase clienteService) {
        this.clienteUseCase = clienteService;
    }

    @PostMapping(value="/crear",consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> crearCliente(@RequestBody ClienteModel clienteModel) {
        System.out.println("Cliente recibido: " + clienteModel);

        Cliente cliente= clienteUseCase.createCliente(clienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable String id) {
        Cliente cliente = clienteUseCase.getClienteById(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteUseCase.getAllClientes();
        return ResponseEntity.ok(clientes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable String id) {
        try {
            clienteUseCase.deleteCliente(id);
            return ResponseEntity.ok("Cliente eliminado");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}

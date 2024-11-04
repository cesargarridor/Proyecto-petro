package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller;

import com.libreriacesar.core_microservices_cliente_service.Cliente.application.port.ClientUseCase;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Mappers.ClienteMapper;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.ClienteModel;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.output.ClienteSalida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<ClienteSalida> obtenerCliente(@PathVariable String id) {
        Cliente cliente = clienteUseCase.getClienteById(id);
        if (cliente != null) {
            ClienteSalida clienteSalida = ClienteMapper.INSTANCE.clienteToClienteSalida(cliente);
            return ResponseEntity.ok(clienteSalida);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



    @GetMapping("/")
    public ResponseEntity<List<ClienteSalida>> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteUseCase.getAllClientes();
        List<ClienteSalida> clientesSalida = clientes.stream()
                .map(ClienteMapper.INSTANCE::clienteToClienteSalida)
                .collect(Collectors.toList());

        return ResponseEntity.ok(clientesSalida);
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

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarCliente(@RequestBody ClienteModel clienteModel) {
        try {
            clienteUseCase.updateCliente(clienteModel);
            return ResponseEntity.ok("Cliente actualizado");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}

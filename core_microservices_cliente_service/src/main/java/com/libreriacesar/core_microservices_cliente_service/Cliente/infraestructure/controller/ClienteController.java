package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller;

import com.libreriacesar.core_microservices_cliente_service.Cliente.application.port.ClientUseCase;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Mappers.ClienteMapper;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.ClienteModel;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.output.ClientePresupuestoDTO;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.output.ClienteSalida;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private final ClientUseCase clienteUseCase;

    @Autowired
    public ClienteController(ClientUseCase clienteService) {
        this.clienteUseCase = clienteService;
    }

    @ApiOperation(value = "Crear un cliente")
    @PostMapping(value="/crear", consumes = "application/json")
    public ResponseEntity<String> crearCliente(@RequestBody ClienteModel clienteModel) {
        clienteUseCase.createCliente(clienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado");
    }

    @ApiOperation(value = "Obtener un cliente por ID")
    @GetMapping("/buscarPorId")
    @ResponseBody
    public ResponseEntity<ClienteSalida> obtenerCliente(
            @ApiParam(value = "ID del cliente a buscar", required = true) @RequestParam String id) {
        logger.info("Buscando cliente con ID: {}", id);
        Cliente cliente = clienteUseCase.getClienteById(id);
        if (cliente != null) {
            ClienteSalida clienteSalida = ClienteMapper.INSTANCE.clienteToClienteSalida(cliente);
            return ResponseEntity.ok(clienteSalida);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @ApiOperation(value = "Obtener clientes por valor de SK")
    @GetMapping("/buscarPorSk")
    public ResponseEntity<List<ClienteSalida>> buscarClientesPorSk(
            @ApiParam(value = "Valor de SK para buscar clientes", required = true) @RequestParam String skValue) {
        List<Cliente> clientes = clienteUseCase.findAllClientesBySk(skValue);
        List<ClienteSalida> clientesSalida = clientes.stream()
                .map(ClienteMapper.INSTANCE::clienteToClienteSalida)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientesSalida);
    }

    @ApiOperation(value = "Obtener todos los clientes")
    @GetMapping("/")
    public ResponseEntity<List<ClienteSalida>> buscarTodosLosClientes() {
        String skValue = "clientData";
        List<Cliente> clientes = clienteUseCase.findAllClientesBySk(skValue);
        List<ClienteSalida> clientesSalida = clientes.stream()
                .map(ClienteMapper.INSTANCE::clienteToClienteSalida)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientesSalida);
    }

    @ApiOperation(value = "Obtener un cliente con su presupuesto por ID")
    @GetMapping("/{clienteId}/conPresupuesto")
    @ResponseBody
    public ResponseEntity<ClientePresupuestoDTO> obtenerClienteConPresupuesto(
            @ApiParam(value = "ID del cliente", required = true) @PathVariable String clienteId) {
        ClientePresupuestoDTO response = clienteUseCase.findClienteAndPresupuestoByClientId(clienteId);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Obtener todos los clientes con presupuesto")
    @GetMapping("/findAll")
    @ResponseBody
    public ResponseEntity<List<ClientePresupuestoDTO>> obtenerClientesEnteros() {
        return ResponseEntity.ok(clienteUseCase.listarClientesConPresupuesto());
    }

    @ApiOperation(value = "Eliminar un cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(
            @ApiParam(value = "ID del cliente a eliminar", required = true) @PathVariable String id) {
        logger.info("Eliminando cliente con ID: {}", id);
        try {
            clienteUseCase.deleteCliente(id);
            return ResponseEntity.ok("Cliente eliminado");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ApiOperation(value = "Actualizar un cliente")
    @PutMapping("/actualizar")
    @ResponseBody
    public ResponseEntity<String> actualizarCliente(
            @ApiParam(value = "Modelo con datos actualizados del cliente", required = true) @RequestBody ClienteModel clienteModel) {
        try {
            clienteUseCase.updateCliente(clienteModel);
            return ResponseEntity.ok("Cliente actualizado");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

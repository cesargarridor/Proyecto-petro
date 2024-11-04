package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller;

import com.libreriacesar.core_microservices_cliente_service.Cliente.application.port.ClientUseCase;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Mappers.ClienteMapper;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Presupuesto;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.ClienteModel;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.output.ClientePresupuestoDTO;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.output.ClienteSalida;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository.port.ClienteRepository;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository.port.PresupuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteMapper clienteMapper;
    private final ClientUseCase clienteUseCase;
    private PresupuestoRepository presupuestoRepository;
    private ClienteRepository clienteRepository;


    @Autowired
    public ClienteController(ClientUseCase clienteService) {
        this.clienteUseCase = clienteService;
    }

    @PostMapping(value="/crear",consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> crearCliente(@RequestBody ClienteModel clienteModel) {
        System.out.println("Cliente recibido: " + clienteModel);

        clienteUseCase.createCliente(clienteModel);


        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado");
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteSalida> obtenerCliente(@PathVariable String id) {
        Cliente cliente = clienteUseCase.getClienteById(id);
        System.out.println(cliente);
        if (cliente != null) {
            ClienteSalida clienteSalida = ClienteMapper.INSTANCE.clienteToClienteSalida(cliente);
            return ResponseEntity.ok(clienteSalida);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @GetMapping("/buscarPorSk")
    public ResponseEntity<List<ClienteSalida>> buscarClientesPorSk(@RequestParam String skValue) {
        List<Cliente> clientes = clienteUseCase.findAllClientesBySk(skValue);
        List<ClienteSalida> clientesSalida = clientes.stream()
                .map(ClienteMapper.INSTANCE::clienteToClienteSalida)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientesSalida);
    }

    @GetMapping("/")
    public ResponseEntity<List<ClienteSalida>> buscarTodosLosClientes() {
        String skValue="clientData";
        List<Cliente> clientes = clienteUseCase.findAllClientesBySk(skValue);
        List<ClienteSalida> clientesSalida = clientes.stream()
                .map(ClienteMapper.INSTANCE::clienteToClienteSalida)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientesSalida);
    }

    @GetMapping("/{clienteId}/conPresupuesto")
    public ResponseEntity<ClientePresupuestoDTO> obtenerClienteConPresupuesto(@PathVariable String clienteId) {
        ClientePresupuestoDTO response = clienteUseCase.findClienteAndPresupuestoByClientId(clienteId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ClientePresupuestoDTO>> obtenerClientesEnteros(){
        return ResponseEntity.ok(clienteUseCase.listarClientesConPresupuesto());
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


   

    @GetMapping("/activos")
    public ResponseEntity<List<Cliente>> getClientesActivos() {

        List<Cliente> clientesActivos = clienteRepository.findByEstadoTrue();
        return ResponseEntity.ok(clientesActivos);
    }

    @GetMapping("/inactivos")
    public ResponseEntity<List<Cliente>> getClientesInactivos() {
        List<Cliente> clientesInactivos = clienteRepository.findByEstadoFalse();
        return ResponseEntity.ok(clientesInactivos);
    }

    @GetMapping("/presupuestos/activos")
    public ResponseEntity<List<Presupuesto>> getPresupuestosActivos() {
        List<Presupuesto> presupuestosActivos = presupuestoRepository.findByEnabledTrue();
        return ResponseEntity.ok(presupuestosActivos);
    }

    @GetMapping("/presupuestos/inactivos")
    public ResponseEntity<List<Presupuesto>> getPresupuestosInactivos() {
        List<Presupuesto> presupuestosInactivos = presupuestoRepository.findByEnabledFalse();
        return ResponseEntity.ok(presupuestosInactivos);
    }


}

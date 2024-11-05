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
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para gestionar las operaciones de clientes.
 * Proporciona endpoints para crear, actualizar, eliminar y consultar clientes,
 * así como obtener clientes junto con sus presupuestos.
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private ClienteMapper clienteMapper;
    @Autowired
    private final ClientUseCase clienteUseCase;
    private PresupuestoRepository presupuestoRepository;
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClientUseCase clienteService) {
        this.clienteUseCase = clienteService;
    }

    /**
     * Crea un nuevo cliente.
     *
     * @param clienteModel los datos del cliente a crear.
     * @return una respuesta HTTP con el estado de la operación.
     */
    @ApiOperation(value = "Crear un cliente")
    @PostMapping(value="/crear", consumes = "application/json")
    public ResponseEntity<String> crearCliente(@RequestBody ClienteModel clienteModel) {

        clienteUseCase.createCliente(clienteModel);

        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado");
    }

    /**
     * Obtiene un cliente por su ID.
     *
     * @param id ID del cliente.
     * @return el cliente encontrado o un estado HTTP 404 si no se encuentra.
     */
    @ApiOperation(value = "Obtener un cliente por id")
    @GetMapping("/buscarPorId")
    @ResponseBody
    public ResponseEntity<ClienteSalida> obtenerCliente(@RequestParam String id) {
        logger.info("Buscando cliente con ID: {}", id);

        Cliente cliente = clienteUseCase.getClienteById(id);
        if (cliente != null) {
            ClienteSalida clienteSalida = ClienteMapper.INSTANCE.clienteToClienteSalida(cliente);
            return ResponseEntity.ok(clienteSalida);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Obtiene una lista de clientes que coinciden con un valor de sk.
     *
     * @param skValue el valor de sk para buscar clientes.
     * @return la lista de clientes encontrados.
     */
    @ApiOperation(value = "Obtener cliente por la sk")
    @GetMapping("/buscarPorSk")
    public ResponseEntity<List<ClienteSalida>> buscarClientesPorSk(@RequestParam String skValue) {

        List<Cliente> clientes = clienteUseCase.findAllClientesBySk(skValue);
        List<ClienteSalida> clientesSalida = clientes.stream()
                .map(ClienteMapper.INSTANCE::clienteToClienteSalida)
                .collect(Collectors.toList());

        return ResponseEntity.ok(clientesSalida);
    }

    /**
     * Obtiene todos los clientes.
     *
     * @return una lista de todos los clientes.
     */
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

    /**
     * Obtiene un cliente junto con su presupuesto por ID.
     *
     * @param clienteId ID del cliente.
     * @return el cliente y su presupuesto.
     */
    @ApiOperation(value = "Obtener un cliente con su presupuesto por id")
    @GetMapping("/{clienteId}/conPresupuesto")
    @ResponseBody
    public ResponseEntity<ClientePresupuestoDTO> obtenerClienteConPresupuesto(@PathVariable String clienteId) {

        ClientePresupuestoDTO response = clienteUseCase.findClienteAndPresupuestoByClientId(clienteId);
        return ResponseEntity.ok(response);
    }

    /**
     * Obtiene todos los clientes junto con sus presupuestos.
     *
     * @return una lista de todos los clientes con presupuestos.
     */
    @ApiOperation(value = "Obtener clientes con su presupuesto")
    @GetMapping("/findAll")
    @ResponseBody
    public ResponseEntity<List<ClientePresupuestoDTO>> obtenerClientesEnteros(){

        return ResponseEntity.ok(clienteUseCase.listarClientesConPresupuesto());
    }

    /**
     * Elimina un cliente por su ID.
     *
     * @param id ID del cliente a eliminar.
     * @return una respuesta con el estado de la operación.
     */
    @ApiOperation(value = "Eliminar un cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable String id) {
        logger.info("Eliminando cliente con ID: {}", id);

        try {
            clienteUseCase.deleteCliente(id);
            return ResponseEntity.ok("Cliente eliminado");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Actualiza los datos de un cliente.
     *
     * @param clienteModel los datos actualizados del cliente.
     * @return una respuesta con el estado de la operación.
     */
    @ApiOperation(value = "Actualizar un cliente ")
    @PutMapping("/actualizar")
    @ResponseBody
    public ResponseEntity<String> actualizarCliente(@RequestBody ClienteModel clienteModel) {

        try {
            clienteUseCase.updateCliente(clienteModel);
            return ResponseEntity.ok("Cliente actualizado");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

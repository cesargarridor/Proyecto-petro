package com.libreriacesar.core_microservices_cliente_service.Cliente.application;

import com.libreriacesar.core_microservices_cliente_service.Cliente.application.port.ClientUseCase;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Mappers.ClienteMapper;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Mappers.PresupuestoMapper;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Presupuesto;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.ClienteModel;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.output.ClientePresupuestoDTO;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository.port.ClienteRepository;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository.port.PresupuestoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class ClienteUseCaseImpl implements ClientUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ClienteUseCaseImpl.class);

    private final ClienteRepository clienteRepository;
    private final PresupuestoRepository presupuestoRepository;

    public ClienteUseCaseImpl(ClienteRepository clienteRepository, PresupuestoRepository presupuestoRepository) {
        this.clienteRepository = clienteRepository;
        this.presupuestoRepository = presupuestoRepository;
    }



    /**
     * Crea un nuevo Cliente a partir de la información proporcionada en ClienteModel.
     *
     * @param clienteModel Objeto DTO que contiene la información del cliente.
     * @return El objeto Cliente creado.
     * @throws RuntimeException si un cliente con el mismo ID ya existe.
     */
    @Override
    public Cliente createCliente(ClienteModel clienteModel) {
        logger.info("Intentando crear un nuevo cliente con ID: {}", clienteModel.getClientId());

        if (clienteRepository.findById(clienteModel.getClientId()) != null) {
            logger.error("El cliente con ID {} ya existe", clienteModel.getClientId());
            throw new RuntimeException("El cliente con ID " + clienteModel.getClientId() + " ya existe.");
        }

        Cliente cliente = ClienteMapper.INSTANCE.modelToEntity(clienteModel);


        clienteRepository.save(cliente);
        logger.info("Cliente con ID {} creado exitosamente", clienteModel.getClientId());

        if (clienteModel.getPresupuesto() != null) {
            Presupuesto presupuesto = PresupuestoMapper.INSTANCE.modelToEntity(clienteModel.getPresupuesto());
            presupuesto.setPk(cliente.getPk());
            presupuesto.setSk(Presupuesto.PATTERN_SK);

            presupuestoRepository.save(presupuesto);
            logger.info("Presupuesto para el cliente con ID {} creado exitosamente", clienteModel.getClientId());
        }

        return cliente;
    }



    /**
     * Recupera un cliente por su ID.
     *
     * @param id El ID del cliente a recuperar.
     * @return El cliente con el ID especificado o null si no se encuentra.
     */
    @Override
    public Cliente getClienteById(String id) {
        logger.info("Recuperando cliente con ID: {}", id);
        return clienteRepository.findById(id);
    }



    /**
     * Recupera todos los clientes.
     *
     * @return Lista de todos los clientes.
     */
    @Override
    public List<Cliente> getAllClientes() {
        logger.info("Recuperando todos los clientes");
        return clienteRepository.findAll();
    }



    /**
     * Elimina un cliente por su ID.
     *
     * @param id El ID del cliente a eliminar.
     * @throws RuntimeException si el cliente con el ID especificado no se encuentra.
     */
    @Override
    public void deleteCliente(String id) {
        logger.info("Intentando eliminar el cliente con ID: {}", id);
        Cliente cliente = getClienteById(id);
        if (cliente != null) {
            clienteRepository.delete(cliente);
            logger.info("Cliente con ID {} eliminado exitosamente", id);
        } else {
            logger.error("Cliente con ID {} no encontrado", id);
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
    }



    /**
     * Actualiza los detalles de un cliente.
     *
     * @param clienteModel Objeto DTO que contiene la información actualizada del cliente.
     * @throws RuntimeException si el cliente con el ID especificado no se encuentra.
     */
    @Override
    public void updateCliente(ClienteModel clienteModel) {
        logger.info("Actualizando el cliente con ID: {}", clienteModel.getClientId());

        Cliente clienteExistente = clienteRepository.findById(clienteModel.getClientId());

        if (clienteExistente == null) {
            logger.error("Cliente con ID {} no encontrado", clienteModel.getClientId());
            throw new RuntimeException("Cliente no encontrado con ID: " + clienteModel.getClientId());
        }

        Cliente clienteActualizado = ClienteMapper.INSTANCE.modelToEntity(clienteModel);


        clienteRepository.save(clienteActualizado);
        logger.info("Cliente con ID {} actualizado exitosamente", clienteModel.getClientId());
    }




    /**
     * Recupera todos los clientes con el valor de clave secundaria (skValue) especificado.
     *
     * @param skValue El valor de la clave secundaria para filtrar clientes.
     * @return Lista de clientes que coinciden con el skValue.
     */
    @Override
    public List<Cliente> findAllClientesBySk(String skValue) {
        logger.info("Recuperando todos los clientes ");
        return clienteRepository.findAllClientesBySk(skValue);
    }



    /**
     * Encuentra un cliente junto con su presupuesto por ID del cliente.
     *
     * @param clientId El ID del cliente.
     * @return Un objeto ClientePresupuestoDTO con datos del cliente y presupuesto, o null si no se encuentra.
     */
    @Override
    public ClientePresupuestoDTO findClienteAndPresupuestoByClientId(String clientId) {
        logger.info("Recuperando cliente y presupuesto para el ID de cliente: {}", clientId);
        return clienteRepository.findClienteAndPresupuestoByClientId(clientId);
    }



    /**
     * Lista todos los clientes junto con sus presupuestos.
     *
     * @return Lista de objetos ClientePresupuestoDTO con datos del cliente y presupuesto.
     */
    public List<ClientePresupuestoDTO> listarClientesConPresupuesto() {
        logger.info("Listando todos los clientes con sus presupuestos");
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream().map(cliente -> {
            Presupuesto presupuesto = presupuestoRepository.findById(cliente.getClientId());
            return new ClientePresupuestoDTO(cliente, presupuesto);
        }).collect(Collectors.toList());
    }
}

package com.libreriacesar.core_microservices_cliente_service.Cliente.application.port;

import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.ClienteModel;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.output.ClientePresupuestoDTO;

import java.util.List;

public interface ClientUseCase {
    Cliente createCliente(ClienteModel clienteModel);
    Cliente getClienteById(String id);
    List<Cliente> getAllClientes();
    void deleteCliente(String id);
    void updateCliente(ClienteModel clienteModel);
    List<Cliente> findAllClientesBySk(String skValue);
    ClientePresupuestoDTO findClienteAndPresupuestoByClientId(String clientId);
    List<ClientePresupuestoDTO> listarClientesConPresupuesto();
}

package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository.port;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.output.ClientePresupuestoDTO;
import com.libreriacesar.core_microservices_cliente_service.shared.utils.Utils;
import com.libreriacesar.core_microservices_cliente_service.shared.utils.UtilsDynamoDB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ClienteRepository {
    void save(Cliente cliente);
    Cliente findById(String id);
    List<Cliente> findAll();
    void delete(Cliente cliente);

    List<Cliente> findByEstadoTrue();

    List<Cliente> findByEstadoFalse();
    List<Cliente> findAllClientesBySk(String skValue);

    ClientePresupuestoDTO findClienteAndPresupuestoByClientId(String clientId);
}

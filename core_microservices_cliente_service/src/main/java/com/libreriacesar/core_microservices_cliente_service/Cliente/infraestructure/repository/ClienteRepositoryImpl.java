package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Presupuesto;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.output.ClientePresupuestoDTO;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository.port.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public ClienteRepositoryImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public void save(Cliente cliente) {
        if (cliente.getId() == null || cliente.getId().isEmpty()) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo o vacío");
        }
        dynamoDBMapper.save(cliente);
    }



    public List<Cliente> findAllClientesBySk(String skValue) {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("SK = :skValue")
                .withExpressionAttributeValues(Map.of(":skValue", new AttributeValue().withS(skValue)));

        return dynamoDBMapper.scan(Cliente.class, scanExpression);
    }


    public ClientePresupuestoDTO findClienteAndPresupuestoByClientId(String clientId) {
        Cliente cliente = dynamoDBMapper.load(Cliente.class, clientId, Cliente.PATTERN_SK);

        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado con ID: " + clientId);
        }

        Presupuesto presupuesto = dynamoDBMapper.load(Presupuesto.class, clientId, Presupuesto.PATTERN_SK);

        return new ClientePresupuestoDTO(cliente, presupuesto);
    }


    @Override
    public Cliente findById(String clientId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":pk", new AttributeValue().withS(clientId));
        eav.put(":sk", new AttributeValue().withS(Cliente.PATTERN_SK)); // Asegurando que SK sea el patrón para Cliente

        DynamoDBQueryExpression<Cliente> queryExpression = new DynamoDBQueryExpression<Cliente>()
                .withKeyConditionExpression("PK = :pk and SK = :sk")
                .withExpressionAttributeValues(eav);

        List<Cliente> result = dynamoDBMapper.query(Cliente.class, queryExpression);
        return result.isEmpty() ? null : result.get(0);
    }




    @Override
    public List<Cliente> findAll() {
        return dynamoDBMapper.scan(Cliente.class, new DynamoDBScanExpression());
    }

    @Override
    public void delete(Cliente cliente) {
        dynamoDBMapper.delete(cliente);
    }

    @Override
    public List<Cliente> findByEstadoTrue() {
        return List.of();
    }

    @Override
    public List<Cliente> findByEstadoFalse() {
        return List.of();
    }
}
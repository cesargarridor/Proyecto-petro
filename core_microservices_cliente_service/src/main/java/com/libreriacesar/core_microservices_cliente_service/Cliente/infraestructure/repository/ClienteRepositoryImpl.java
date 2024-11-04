package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository.port.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public Cliente findById(String id) {
        return dynamoDBMapper.load(Cliente.class, id);
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
package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Presupuesto;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository.port.PresupuestoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PresupuestoRepositoryImpl implements PresupuestoRepository {
    private final DynamoDBMapper dynamoDBMapper;

    public PresupuestoRepositoryImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public void save(Presupuesto presupuesto) {
        dynamoDBMapper.save(presupuesto);
    }

    @Override
    public Presupuesto findById(String id) {
        return dynamoDBMapper.load(Presupuesto.class, id);
    }

    @Override
    public List<Presupuesto> findAll() {
        return List.of();
    }

    @Override
    public void delete(Presupuesto presupuesto) {

    }

    @Override
    public List<Presupuesto> findByEnabledTrue() {
        return List.of();
    }

    @Override
    public List<Presupuesto> findByEnabledFalse() {
        return List.of();
    }
}

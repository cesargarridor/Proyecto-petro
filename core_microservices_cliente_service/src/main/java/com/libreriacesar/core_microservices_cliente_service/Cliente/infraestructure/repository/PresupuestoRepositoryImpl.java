package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Presupuesto;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository.port.PresupuestoRepository;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Presupuesto findById(String presupuestoId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":pk", new AttributeValue().withS(presupuestoId));  // Usar presupuestoId como PK
        eav.put(":sk", new AttributeValue().withS(Presupuesto.PATTERN_SK));  // Usar un patrón de SK para el presupuesto

        DynamoDBQueryExpression<Presupuesto> queryExpression = new DynamoDBQueryExpression<Presupuesto>()
                .withKeyConditionExpression("PK = :pk and SK = :sk")  // Condición para PK y SK
                .withExpressionAttributeValues(eav);

        List<Presupuesto> result = dynamoDBMapper.query(Presupuesto.class, queryExpression);

        return result.isEmpty() ? null : result.get(0);  // Retorna el primer resultado o null si no se encuentra
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

package gastos.core_microservice_gastos.Cliente.infraestructure.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import gastos.core_microservice_gastos.Cliente.domain.Gasto;
import gastos.core_microservice_gastos.Cliente.infraestructure.repository.port.GastosRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GastosRepositoryImpl implements GastosRepository {

    private final DynamoDBMapper dynamoDBMapper;


    public GastosRepositoryImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public void save(Gasto gasto) {
        dynamoDBMapper.save(gasto);
    }
    @Override
    public Gasto findByGastoId(String clientId, String gastoId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":pk", new AttributeValue().withS(clientId));
        eav.put(":sk", new AttributeValue().withS(Gasto.PATTERN_SK + gastoId));

        DynamoDBQueryExpression<Gasto> queryExpression = new DynamoDBQueryExpression<Gasto>()
                .withKeyConditionExpression("PK = :pk and SK = :sk")
                .withExpressionAttributeValues(eav);

        List<Gasto> result = dynamoDBMapper.query(Gasto.class, queryExpression);
        return result.isEmpty() ? null : result.get(0);
    }




}

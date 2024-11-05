package gastos.core_microservice_gastos.Cliente.infraestructure.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import gastos.core_microservice_gastos.Cliente.domain.Gasto;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.GastoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.repository.port.GastosRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GastosRepositoryImpl implements GastosRepository {

    private final DynamoDBMapper dynamoDBMapper;


    public GastosRepositoryImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public void save(GastoModel gastoModel) {
        dynamoDBMapper.save(gastoModel);
    }
}

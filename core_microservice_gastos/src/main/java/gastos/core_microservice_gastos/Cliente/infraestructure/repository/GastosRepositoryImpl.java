package gastos.core_microservice_gastos.Cliente.infraestructure.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import gastos.core_microservice_gastos.Cliente.domain.Gasto;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.GastoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.repository.port.GastosRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
    public Optional<Gasto> findByClientIdAndGastoId(String clientId, String gastoId) {
        Gasto gasto = new Gasto(clientId);
        gasto.setGastoId(gastoId);

        Gasto existingGasto = dynamoDBMapper.load(gasto);
        return Optional.ofNullable(existingGasto);
    }
}

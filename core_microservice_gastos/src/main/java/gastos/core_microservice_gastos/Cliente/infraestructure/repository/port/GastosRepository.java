package gastos.core_microservice_gastos.Cliente.infraestructure.repository.port;

import gastos.core_microservice_gastos.Cliente.domain.Gasto;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.GastoModel;

import java.util.Optional;

public interface GastosRepository {
    void save(Gasto gasto);


    Gasto findByGastoId(String clientId, String gastoId);
}

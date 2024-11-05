package gastos.core_microservice_gastos.Cliente.infraestructure.repository.port;

import gastos.core_microservice_gastos.Cliente.domain.Gasto;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.GastoModel;

public interface GastosRepository {
    void save(GastoModel gastoModel);
}

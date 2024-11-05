package gastos.core_microservice_gastos.Cliente.application.port;

import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.ClienteModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.output.ClientePresupuestoDTO;

import java.util.List;

public interface GastosUseCase {
     ClienteModel obtenerClientePorId(String id);
     List<ClientePresupuestoDTO> obtenerTodo();
}

package gastos.core_microservice_gastos.Cliente.application.port;

import gastos.core_microservice_gastos.Cliente.domain.Gasto;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.ClienteModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.GastoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.PresupuestoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.output.ClientePresupuestoDTO;

import java.util.List;

public interface GastosUseCase {
     ClienteModel obtenerClientePorId(String id);
     List<ClientePresupuestoDTO> obtenerTodo();
     PresupuestoModel restar(String id,double cantidad);
     PresupuestoModel sumar(String id,double cantidad);
    // void guardarNuevoGasto(GastoModel gastoModel);
     PresupuestoModel buscarPorId(String id);

}

package gastos.core_microservice_gastos.Cliente.application.port;

import gastos.core_microservice_gastos.Cliente.domain.Gasto;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.ClienteModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.GastoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.PresupuestoModel;
import org.springframework.web.bind.annotation.RequestParam;

public interface GastosUseCase {
     ClienteModel obtenerClientePorId(String id);
     PresupuestoModel restar(String id,double cantidad);
     PresupuestoModel sumar(String id,double cantidad);
     Gasto guardarNuevoGasto(GastoModel gastoModel);
     PresupuestoModel buscarPorId(String id);
     Double getCantidadPresupuesto(@RequestParam(name="id") String id);
     Gasto modificarGasto(GastoModel gastoModel);
     Gasto modificarEstado(GastoModel gastoModel);
}

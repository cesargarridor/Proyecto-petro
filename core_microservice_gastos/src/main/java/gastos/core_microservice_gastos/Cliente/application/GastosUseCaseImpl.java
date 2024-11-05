package gastos.core_microservice_gastos.Cliente.application;

import gastos.core_microservice_gastos.Cliente.application.feign.ClienteServiceClient;
import gastos.core_microservice_gastos.Cliente.application.feign.PresupuestoServiceClient;
import gastos.core_microservice_gastos.Cliente.application.port.GastosUseCase;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.ClienteModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.GastoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.PresupuestoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.output.ClientePresupuestoDTO;
import gastos.core_microservice_gastos.Cliente.infraestructure.repository.port.GastosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GastosUseCaseImpl implements GastosUseCase {

    private final ClienteServiceClient clienteServiceClient;
    private final PresupuestoServiceClient presupuestoServiceClient;

    private final GastosRepository gastosRepository;

    public GastosUseCaseImpl(ClienteServiceClient clienteServiceClient, PresupuestoServiceClient presupuestoServiceClient, GastosRepository gastosRepository) {
        this.clienteServiceClient = clienteServiceClient;
        this.presupuestoServiceClient = presupuestoServiceClient;
        this.gastosRepository = gastosRepository;
    }

    @Override
    public ClienteModel obtenerClientePorId(String id) {
        return clienteServiceClient.findById(id);
    }

    @Override
    public List<ClientePresupuestoDTO> obtenerTodo() {
        return (List<ClientePresupuestoDTO>) clienteServiceClient.findAll();
    }

    @Override
    public PresupuestoModel restar(String id,double cantidad) {
        return presupuestoServiceClient.restar(id,cantidad);
    }

    @Override
    public PresupuestoModel sumar(String id,double cantidad) {
        return presupuestoServiceClient.sumar(id,cantidad);
    }

    @Override
    public PresupuestoModel buscarPorId(String id) {
        System.out.println("id="+id);
        return presupuestoServiceClient.buscarPorId(id);
    }

   /* @Override
    public void guardarNuevoGasto(GastoModel gastoModel) {
        // Conversión manual de GastoModel a Gasto
        Gasto gasto = new Gasto(gastoModel.getClientId());
        gasto.setGastoId(gastoModel.getGastoId());
        gasto.setCantidad(gastoModel.getCantidad());
        gasto.setEstado(gastoModel.isEstado());

        // Obtener el presupuesto correspondiente
        Presupuesto presupuesto = presupuestoRepository.findById(gastoModel.getClientId());
        if (presupuesto == null) {
            throw new RuntimeException("Presupuesto no encontrado para el clientId: " + gastoModel.getClientId());
        }

        // Verificar que el presupuesto tenga suficiente cantidad antes de restar
        if (presupuesto.getCantidad() < gasto.getCantidad()) {
            throw new RuntimeException("Fondos insuficientes en el presupuesto para realizar el gasto");
        }

        // Restar la cantidad de gasto del presupuesto
        presupuesto.setCantidad(presupuesto.getCantidad() - gasto.getCantidad());

        // Guardar el gasto y actualizar el presupuesto en la base de datos
        gastosRepository.save(gasto);
        presupuestoRepository.save(presupuesto);
    }*/



}

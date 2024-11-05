package gastos.core_microservice_gastos.Cliente.application;

import gastos.core_microservice_gastos.Cliente.application.feign.ClienteServiceClient;
import gastos.core_microservice_gastos.Cliente.application.feign.PresupuestoServiceClient;
import gastos.core_microservice_gastos.Cliente.application.port.GastosUseCase;
import gastos.core_microservice_gastos.Cliente.domain.Gasto;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.ClienteModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.GastoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.PresupuestoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.output.ClientePresupuestoDTO;
import gastos.core_microservice_gastos.Cliente.infraestructure.repository.port.GastosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Double getCantidadPresupuesto(String id) {
        return presupuestoServiceClient.getCantidad(id);
    }

    @Override
    public Gasto guardarNuevoGasto(GastoModel gastomodel) {
        Optional<Gasto> gastoExistente = gastosRepository.findByClientIdAndGastoId(gastomodel.getClientId(), gastomodel.getGastoId());

        if (gastoExistente.isPresent()) {
            throw new RuntimeException("El gastoId ya existe para este cliente.");
        }

        double cantidadPresupuesto = getCantidadPresupuesto(gastomodel.getClientId());

        if (cantidadPresupuesto <= 0 || gastomodel.getCantidad() > cantidadPresupuesto) {
            throw new RuntimeException("No hay suficiente cantidad en el presupuesto o estás en negativo.");
        }

        Gasto gasto = new Gasto(gastomodel.getClientId());
        gasto.setPk(gastomodel.getClientId());
        gasto.setSk("gastoId#" + gastomodel.getGastoId());
        gasto.setGastoId(gastomodel.getGastoId());
        gasto.setCantidad(gastomodel.getCantidad());
        gasto.setEstado(gastomodel.isEstado());

        restar(gastomodel.getClientId(), gastomodel.getCantidad());
        gastosRepository.save(gasto);

        return gasto;
    }







}





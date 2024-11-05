package gastos.core_microservice_gastos.Cliente.application;

import gastos.core_microservice_gastos.Cliente.application.feign.ClienteServiceClient;
import gastos.core_microservice_gastos.Cliente.application.feign.PresupuestoServiceClient;
import gastos.core_microservice_gastos.Cliente.application.port.GastosUseCase;
import gastos.core_microservice_gastos.Cliente.domain.Gasto;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.ClienteModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.GastoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.PresupuestoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.repository.port.GastosRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    public Double getCantidadPresupuesto(String clientId) {
        double presupuestoTotal = presupuestoServiceClient.getCantidad(clientId);
        double totalGastosActivos = gastosRepository.obtenerTotalGastosActivos(clientId);

        return presupuestoTotal - totalGastosActivos;
    }

    @Override
    public Gasto modificarGasto(GastoModel gastoModel) {
        Gasto gastoExistente = gastosRepository.findByGastoId(gastoModel.getClientId(), gastoModel.getGastoId());

        if (gastoExistente == null) {
            throw new RuntimeException("El gasto con ID " + gastoModel.getGastoId() + "del cliente "+gastoModel.getClientId()+" no existe.");
        }

        double cantidadAnterior = gastoExistente.getCantidad();
        double cantidadNueva = gastoModel.getCantidad();
        double diferencia = cantidadNueva - cantidadAnterior;


        if (diferencia > 0) {
            if (getCantidadPresupuesto(gastoModel.getClientId()) >= diferencia) {
                presupuestoServiceClient.restar(gastoModel.getClientId(), diferencia);
            } else {
                throw new RuntimeException("No hay suficiente presupuesto para esta modificación.");
            }
        }
        else if (diferencia < 0) {
            presupuestoServiceClient.sumar(gastoModel.getClientId(), Math.abs(diferencia));
        }

        gastoExistente.setCantidad(cantidadNueva);
        gastosRepository.save(gastoExistente);

        return gastoExistente;
    }


    @Override
    public Gasto modificarEstado(GastoModel gastoModel) {
        Gasto gastoExistente = gastosRepository.findByGastoId(gastoModel.getClientId(), gastoModel.getGastoId());

        if (gastoExistente == null) {
            throw new RuntimeException("El gasto con ID " + gastoModel.getGastoId() + " del cliente " + gastoModel.getClientId() + " no existe.");
        }

        boolean estadoAnterior = gastoExistente.isEstado();
        boolean nuevoEstado = gastoModel.isEstado();

        if (estadoAnterior != nuevoEstado) {
            if (nuevoEstado) {
                presupuestoServiceClient.restar(gastoModel.getClientId(), gastoExistente.getCantidad());
            } else {
                presupuestoServiceClient.sumar(gastoModel.getClientId(), gastoExistente.getCantidad());
            }
        }

        gastoExistente.setEstado(nuevoEstado);
        gastosRepository.save(gastoExistente);

        return gastoExistente;
    }




    @Override
    public Gasto guardarNuevoGasto(GastoModel gastoModel) {
        Gasto gastoExistente = gastosRepository.findByGastoId(gastoModel.getClientId(), gastoModel.getGastoId());

        if (gastoExistente != null) {
            throw new RuntimeException("El gasto con ID " + gastoModel.getGastoId() + "del cliente "+gastoModel.getClientId()+" ya existe.");
        }


        if (gastoModel.getGastoId() == null || gastoModel.getGastoId().isEmpty()) {
            gastoModel.setGastoId(UUID.randomUUID().toString());
        }

        Gasto gasto = new Gasto(gastoModel.getClientId(), gastoModel.getGastoId());
        gasto.setPk(gastoModel.getClientId());
        gasto.setCantidad(gastoModel.getCantidad());
        gasto.setEstado(gastoModel.isEstado());

        restar(gastoModel.getClientId(), gastoModel.getCantidad());

        gastosRepository.save(gasto);
        return gasto;
    }









}





package gastos.core_microservice_gastos.Cliente.application;

import gastos.core_microservice_gastos.Cliente.application.feign.ClienteServiceClient;
import gastos.core_microservice_gastos.Cliente.application.feign.PresupuestoServiceClient;
import gastos.core_microservice_gastos.Cliente.application.port.GastosUseCase;
import gastos.core_microservice_gastos.Cliente.domain.Gasto;
import gastos.core_microservice_gastos.Cliente.domain.Mappers.GastosMapper;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.ClienteModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.GastoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.PresupuestoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.repository.port.GastosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GastosUseCaseImpl implements GastosUseCase {

    private static final Logger logger = LoggerFactory.getLogger(GastosUseCaseImpl.class);
    @Autowired
    private GastosMapper gastosMapper;


    private final ClienteServiceClient clienteServiceClient;
    private final PresupuestoServiceClient presupuestoServiceClient;
    private final GastosRepository gastosRepository;

    public GastosUseCaseImpl(ClienteServiceClient clienteServiceClient,
                             PresupuestoServiceClient presupuestoServiceClient,
                             GastosRepository gastosRepository) {
        this.clienteServiceClient = clienteServiceClient;
        this.presupuestoServiceClient = presupuestoServiceClient;
        this.gastosRepository = gastosRepository;
    }

    /**
     * Obtiene un cliente por su ID a través del cliente service client.
     *
     * @param id El ID del cliente a recuperar.
     * @return Un objeto ClienteModel que representa al cliente encontrado.
     */
    @Override
    public ClienteModel obtenerClientePorId(String id) {
        logger.info("Buscando cliente con ID: {}", id);
        return clienteServiceClient.findById(id);
    }

    /**
     * Resta una cantidad del presupuesto asociado al cliente.
     *
     * @param id      El ID del cliente.
     * @param cantidad La cantidad a restar del presupuesto.
     * @return Un objeto PresupuestoModel que representa el presupuesto actualizado.
     */
    @Override
    public PresupuestoModel restar(String id, double cantidad) {
        logger.info("Restando {} del presupuesto del cliente con ID: {}", cantidad, id);
        return presupuestoServiceClient.restar(id, cantidad);
    }

    /**
     * Suma una cantidad al presupuesto asociado al cliente.
     *
     * @param id      El ID del cliente.
     * @param cantidad La cantidad a sumar al presupuesto.
     * @return Un objeto PresupuestoModel que representa el presupuesto actualizado.
     */
    @Override
    public PresupuestoModel sumar(String id, double cantidad) {
        logger.info("Sumando {} al presupuesto del cliente con ID: {}", cantidad, id);
        return presupuestoServiceClient.sumar(id, cantidad);
    }

    /**
     * Busca un presupuesto por su ID.
     *
     * @param id El ID del presupuesto a buscar.
     * @return Un objeto PresupuestoModel que representa el presupuesto encontrado.
     */
    @Override
    public PresupuestoModel buscarPorId(String id) {
        logger.info("Buscando presupuesto con ID: {}", id);
        return presupuestoServiceClient.buscarPorId(id);
    }

    /**
     * Obtiene la cantidad restante del presupuesto del cliente,
     * considerando los gastos activos.
     *
     * @param clientId El ID del cliente.
     * @return La cantidad restante del presupuesto.
     */
    @Override
    public Double getCantidadPresupuesto(String clientId) {
        logger.info("Calculando la cantidad restante del presupuesto para el cliente con ID: {}", clientId);
        double presupuestoTotal = presupuestoServiceClient.getCantidad(clientId);
        double totalGastosActivos = gastosRepository.obtenerTotalGastosActivos(clientId);
        return presupuestoTotal - totalGastosActivos;
    }

    /**
     * Modifica un gasto existente con los nuevos valores.
     *
     * @param gastoModel Objeto que contiene los nuevos valores del gasto.
     * @return El objeto Gasto actualizado.
     * @throws RuntimeException si el gasto no existe.
     */
    @Override
    public Gasto modificarGasto(GastoModel gastoModel) {
        logger.info("Modificando el gasto con ID: {} del cliente {}", gastoModel.getGastoId(), gastoModel.getClientId());

        Gasto gastoExistente = gastosRepository.findByGastoId(gastoModel.getClientId(), gastoModel.getGastoId());
        if (gastoExistente == null) {
            logger.error("El gasto con ID {} del cliente {} no existe.", gastoModel.getGastoId(), gastoModel.getClientId());
            throw new RuntimeException("El gasto con ID " + gastoModel.getGastoId() + " del cliente " + gastoModel.getClientId() + " no existe.");
        }

        // Calcular la diferencia en la cantidad y ajustar el presupuesto
        double cantidadAnterior = gastoExistente.getCantidad();
        double cantidadNueva = gastoModel.getCantidad();
        double diferencia = cantidadNueva - cantidadAnterior;

        if (diferencia > 0) {
            if (getCantidadPresupuesto(gastoModel.getClientId()) >= diferencia) {
                presupuestoServiceClient.restar(gastoModel.getClientId(), diferencia);
            } else {
                logger.error("No hay suficiente presupuesto para esta modificación.");
                throw new RuntimeException("No hay suficiente presupuesto para esta modificación.");
            }
        } else if (diferencia < 0) {
            presupuestoServiceClient.sumar(gastoModel.getClientId(), Math.abs(diferencia));
        }

        // Actualizar los datos del gasto existente con los valores de gastoModel usando el mapper
        gastoExistente.setCantidad(cantidadNueva);
        gastoExistente.setEstado(gastoModel.isEstado());
        gastosRepository.save(gastoExistente);

        logger.info("Gasto con ID {} modificado exitosamente.", gastoModel.getGastoId());
        return gastoExistente;
    }


    /**
     * Modifica el estado de un gasto existente.
     *
     * @param gastoModel Objeto que contiene el nuevo estado del gasto.
     * @return El objeto Gasto actualizado.
     * @throws RuntimeException si el gasto no existe.
     */
    @Override
    public Gasto modificarEstado(GastoModel gastoModel) {
        logger.info("Modificando el estado del gasto con ID: {} del cliente {}", gastoModel.getGastoId(), gastoModel.getClientId());
        Gasto gastoExistente = gastosRepository.findByGastoId(gastoModel.getClientId(), gastoModel.getGastoId());

        if (gastoExistente == null) {
            logger.error("El gasto con ID {} del cliente {} no existe.", gastoModel.getGastoId(), gastoModel.getClientId());
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
        logger.info("Estado del gasto con ID {} modificado exitosamente.", gastoModel.getGastoId());

        return gastoExistente;
    }

    /**
     * Guarda un nuevo gasto asociado a un cliente.
     *
     * @param gastoModel Objeto que contiene los datos del nuevo gasto.
     * @return El objeto Gasto creado.
     * @throws RuntimeException si el gasto ya existe.
     */
   /* @Autowired
    private GastosMapper gastosMapper;*/




    @Override
    public Gasto guardarNuevoGasto(GastoModel gastoModel) {
        logger.info("Guardando un nuevo gasto para el cliente {} con ID: {}", gastoModel.getClientId(), gastoModel.getGastoId());

        Gasto gastoExistente = gastosRepository.findByGastoId(gastoModel.getClientId(), gastoModel.getGastoId());
        if (gastoExistente != null) {
            logger.error("El gasto con ID {} del cliente {} ya existe.", gastoModel.getGastoId(), gastoModel.getClientId());
            throw new RuntimeException("El gasto con ID " + gastoModel.getGastoId() + " del cliente " + gastoModel.getClientId() + " ya existe.");
        }

        if (gastoModel.getGastoId() == null || gastoModel.getGastoId().isEmpty()) {
            gastoModel.setGastoId(UUID.randomUUID().toString());
        }
        System.out.println(gastoModel);
        Gasto gasto = gastosMapper.modelToEntity(gastoModel);
        System.out.println(gasto);



        restar(gastoModel.getClientId(), gastoModel.getCantidad());
        gastosRepository.save(gasto);

        logger.info("Nuevo gasto guardado para el cliente {} con ID: {}", gastoModel.getClientId(), gastoModel.getGastoId());

        return gasto;
    }


}

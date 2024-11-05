package com.libreriacesar.core_microservices_cliente_service.Cliente.application;

import com.libreriacesar.core_microservices_cliente_service.Cliente.application.port.PresupuestoUseCase;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Presupuesto;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository.port.PresupuestoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Implementación de la lógica de negocio para gestionar el presupuesto de un cliente.
 * Proporciona métodos para sumar, restar, obtener y consultar la cantidad del presupuesto.
 */
@Service
public class PresupuestoUseCaseImpl implements PresupuestoUseCase {

    private static final Logger logger = LoggerFactory.getLogger(PresupuestoUseCaseImpl.class);

    private final PresupuestoRepository presupuestoRepository;



    /**
     * Constructor que inicializa el repositorio de presupuesto.
     *
     * @param presupuestoRepository el repositorio de presupuesto.
     */
    public PresupuestoUseCaseImpl(PresupuestoRepository presupuestoRepository) {
        this.presupuestoRepository = presupuestoRepository;
    }



    /**
     * Suma una cantidad al presupuesto especificado.
     *
     * @param presupuestoId ID del presupuesto.
     * @param cantidad      Cantidad a sumar.
     * @return El presupuesto actualizado.
     * @throws IllegalArgumentException si el presupuesto no se encuentra.
     */
    public Presupuesto sumarCantidad(String presupuestoId, double cantidad) {
        logger.info("Intentando sumar {} al presupuesto con ID {}", cantidad, presupuestoId);

        Presupuesto presupuesto = presupuestoRepository.findById(presupuestoId);
        if (presupuesto == null) {
            logger.error("Presupuesto con ID {} no encontrado", presupuestoId);
            throw new IllegalArgumentException("Presupuesto no encontrado");
        }

        presupuesto.setCantidad(presupuesto.getCantidad() + cantidad);
        presupuestoRepository.save(presupuesto);

        logger.info("Cantidad {} sumada al presupuesto con ID {}. Nuevo total: {}", cantidad, presupuestoId, presupuesto.getCantidad());
        return presupuesto;
    }



    /**
     * Resta una cantidad del presupuesto especificado.
     *
     * @param presupuestoId ID del presupuesto.
     * @param cantidad      Cantidad a restar.
     * @return El presupuesto actualizado.
     * @throws IllegalArgumentException si el presupuesto no se encuentra o si la cantidad en el presupuesto es insuficiente.
     */
    public Presupuesto restarCantidad(String presupuestoId, double cantidad) {
        logger.info("Intentando restar {} del presupuesto con ID {}", cantidad, presupuestoId);

        Presupuesto presupuesto = presupuestoRepository.findById(presupuestoId);
        if (presupuesto == null) {
            logger.error("Presupuesto con ID {} no encontrado", presupuestoId);
            throw new IllegalArgumentException("Presupuesto no encontrado");
        }

        if (presupuesto.getCantidad() < cantidad) {
            logger.error("Cantidad insuficiente en el presupuesto con ID {} para restar {}", presupuestoId, cantidad);
            throw new IllegalArgumentException("No hay suficiente presupuesto para restar esta cantidad");
        }

        presupuesto.setCantidad(presupuesto.getCantidad() - cantidad);
        presupuestoRepository.save(presupuesto);

        logger.info("Cantidad {} restada del presupuesto con ID {}. Nuevo total: {}", cantidad, presupuestoId, presupuesto.getCantidad());
        return presupuesto;
    }



    /**
     * Obtiene un presupuesto por su ID.
     *
     * @param id ID del presupuesto.
     * @return El presupuesto encontrado.
     */
    @Override
    public Presupuesto getPresupuestoById(String id) {
        logger.info("Consultando presupuesto con ID {}", id);
        return presupuestoRepository.findById(id);
    }



    /**
     * Consulta la cantidad disponible en el presupuesto especificado.
     *
     * @param presupuestoId ID del presupuesto.
     * @return Cantidad disponible en el presupuesto.
     */
    @Override
    public double getCantidad(String presupuestoId) {
        logger.info("Consultando cantidad del presupuesto con ID {}", presupuestoId);

        Presupuesto presupuesto = presupuestoRepository.findById(presupuestoId);
        double cantidad = presupuesto.getCantidad();

        logger.info("Cantidad disponible en el presupuesto con ID {}: {}", presupuestoId, cantidad);
        return cantidad;
    }
}

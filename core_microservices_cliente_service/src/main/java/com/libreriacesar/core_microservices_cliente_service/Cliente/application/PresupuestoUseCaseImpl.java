package com.libreriacesar.core_microservices_cliente_service.Cliente.application;

import com.libreriacesar.core_microservices_cliente_service.Cliente.application.port.PresupuestoUseCase;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Presupuesto;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository.port.PresupuestoRepository;
import org.springframework.stereotype.Service;

@Service
public class PresupuestoUseCaseImpl implements PresupuestoUseCase {

    private final PresupuestoRepository presupuestoRepository;

    public PresupuestoUseCaseImpl(PresupuestoRepository presupuestoRepository) {
        this.presupuestoRepository = presupuestoRepository;
    }


    public Presupuesto sumarCantidad(String presupuestoId, double cantidad) {
        Presupuesto presupuesto = presupuestoRepository.findById(presupuestoId);
        if (presupuesto == null) {
            throw new IllegalArgumentException("Presupuesto no encontrado");
        }
        presupuesto.setCantidad(presupuesto.getCantidad() + cantidad);
        presupuestoRepository.save(presupuesto);
        return presupuesto;
    }


    public Presupuesto restarCantidad(String presupuestoId, double cantidad) {
        Presupuesto presupuesto = presupuestoRepository.findById(presupuestoId);
        if (presupuesto == null) {
            throw new IllegalArgumentException("Presupuesto no encontrado");
        }
        if (presupuesto.getCantidad() < cantidad) {
            throw new IllegalArgumentException("No hay suficiente presupuesto para restar esta cantidad");
        }
        presupuesto.setCantidad(presupuesto.getCantidad() - cantidad);
        presupuestoRepository.save(presupuesto);
        return presupuesto;
    }

    @Override
    public Presupuesto getPresupuestoById(String id) {
        return presupuestoRepository.findById(id);
    }

}

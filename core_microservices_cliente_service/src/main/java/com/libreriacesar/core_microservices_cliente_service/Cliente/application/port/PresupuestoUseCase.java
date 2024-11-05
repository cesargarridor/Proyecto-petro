package com.libreriacesar.core_microservices_cliente_service.Cliente.application.port;

import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Presupuesto;

public interface PresupuestoUseCase {
    Presupuesto sumarCantidad(String presupuestoId, double cantidad);
    Presupuesto restarCantidad(String presupuestoId, double cantidad);
}

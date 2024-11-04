package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository.port;

import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Presupuesto;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PresupuestoRepository {
    void save(Presupuesto presupuesto);
    Presupuesto findById(String id);
    List<Presupuesto> findAll();
    void delete(Presupuesto presupuesto);
}

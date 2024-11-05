package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller;

import com.libreriacesar.core_microservices_cliente_service.Cliente.application.port.PresupuestoUseCase;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Presupuesto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/presupuestos")
public class PresupuestoController {

    private final com.libreriacesar.core_microservices_cliente_service.Cliente.application.port.PresupuestoUseCase presupuestoUseCase;

    public PresupuestoController(PresupuestoUseCase presupuestoUseCase) {
        this.presupuestoUseCase = presupuestoUseCase;
    }

    @PostMapping("/sumar")
    public ResponseEntity<Presupuesto> sumarCantidad(@RequestParam String presupuestoId, @RequestParam double cantidad) {
        Presupuesto presupuesto = presupuestoUseCase.sumarCantidad(presupuestoId, cantidad);
        return ResponseEntity.ok(presupuesto);
    }

    @PostMapping("/restar")
    public ResponseEntity<Presupuesto> restarCantidad(@RequestParam String presupuestoId, @RequestParam double cantidad) {
        Presupuesto presupuesto = presupuestoUseCase.restarCantidad(presupuestoId, cantidad);
        return ResponseEntity.ok(presupuesto);
    }
}

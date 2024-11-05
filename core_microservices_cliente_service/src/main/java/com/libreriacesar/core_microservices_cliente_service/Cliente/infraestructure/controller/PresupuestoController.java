package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller;

import com.libreriacesar.core_microservices_cliente_service.Cliente.application.port.PresupuestoUseCase;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Mappers.ClienteMapper;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Presupuesto;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.output.ClienteSalida;
import org.springframework.http.HttpStatus;
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
    @ResponseBody
    public ResponseEntity<Presupuesto> sumarCantidad(@RequestParam String presupuestoId, @RequestParam double cantidad) {
        Presupuesto presupuesto = presupuestoUseCase.sumarCantidad(presupuestoId, cantidad);
        return ResponseEntity.ok(presupuesto);
    }

    @PostMapping("/restar")
    @ResponseBody
    public ResponseEntity<Presupuesto> restarCantidad(@RequestParam String presupuestoId, @RequestParam double cantidad) {
        Presupuesto presupuesto = presupuestoUseCase.restarCantidad(presupuestoId, cantidad);
        return ResponseEntity.ok(presupuesto);
    }

    @GetMapping("/buscarPorId")
    @ResponseBody
    public ResponseEntity<Presupuesto> obtenerPresupuestoPorId(@RequestParam String id) {
        Presupuesto presupuesto = presupuestoUseCase.getPresupuestoById(id);
        System.out.println(presupuesto);
        if (presupuesto != null) {
            return ResponseEntity.ok(presupuesto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/getCantidad")
    public ResponseEntity<Double> obtenerCantidad(@RequestParam String id){
        double cantidad= presupuestoUseCase.getCantidad(id);
        return ResponseEntity.ok(cantidad);
    }


}

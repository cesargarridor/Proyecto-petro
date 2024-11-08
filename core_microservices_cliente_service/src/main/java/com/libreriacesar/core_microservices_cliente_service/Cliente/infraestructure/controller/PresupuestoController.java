package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller;

import com.libreriacesar.core_microservices_cliente_service.Cliente.application.port.PresupuestoUseCase;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Presupuesto;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de presupuestos.
 * Proporciona endpoints para sumar, restar y consultar la cantidad en los presupuestos.
 */
@RestController
@RequestMapping("/presupuestos")
public class PresupuestoController {

    private final PresupuestoUseCase presupuestoUseCase;

    public PresupuestoController(PresupuestoUseCase presupuestoUseCase) {
        this.presupuestoUseCase = presupuestoUseCase;
    }

    /**
     * Suma una cantidad al presupuesto especificado.
     *
     * @param presupuestoId ID del presupuesto al que se le sumará la cantidad.
     * @param cantidad cantidad a sumar.
     * @return el presupuesto actualizado.
     */
    @ApiOperation(value = "Sumar una cantidad al presupuesto", notes = "Suma una cantidad especificada al presupuesto con el ID proporcionado.")
    @PostMapping("/sumar")
    public ResponseEntity<Presupuesto> sumarCantidad(@RequestParam String presupuestoId, @RequestParam double cantidad) {
        Presupuesto presupuesto = presupuestoUseCase.sumarCantidad(presupuestoId, cantidad);
        return ResponseEntity.ok(presupuesto);
    }

    /**
     * Resta una cantidad del presupuesto especificado.
     *
     * @param presupuestoId ID del presupuesto al que se le restará la cantidad.
     * @param cantidad cantidad a restar.
     * @return el presupuesto actualizado.
     */
    @ApiOperation(value = "Restar una cantidad del presupuesto", notes = "Resta una cantidad especificada del presupuesto con el ID proporcionado.")
    @PostMapping("/restar")
    public ResponseEntity<Presupuesto> restarCantidad(@RequestParam String presupuestoId, @RequestParam double cantidad) {
        Presupuesto presupuesto = presupuestoUseCase.restarCantidad(presupuestoId, cantidad);
        return ResponseEntity.ok(presupuesto);
    }

    /**
     * Obtiene un presupuesto por su ID.
     *
     * @param id ID del presupuesto.
     * @return el presupuesto encontrado o un estado HTTP 404 si no se encuentra.
     */
    @ApiOperation(value = "Obtener presupuesto por ID", notes = "Obtiene un presupuesto utilizando el ID proporcionado.")
    @GetMapping("/buscarPorId")
    public ResponseEntity<Presupuesto> obtenerPresupuestoPorId(@RequestParam String id) {
        Presupuesto presupuesto = presupuestoUseCase.getPresupuestoById(id);
        if (presupuesto != null) {
            return ResponseEntity.ok(presupuesto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Obtiene la cantidad actual de un presupuesto por su ID.
     *
     * @param id ID del presupuesto.
     * @return la cantidad actual del presupuesto.
     */
    @ApiOperation(value = "Obtener cantidad de presupuesto por ID", notes = "Obtiene la cantidad actual en el presupuesto con el ID proporcionado.")
    @GetMapping("/getCantidad")
    public ResponseEntity<Double> obtenerCantidad(@RequestParam String id) {
        double cantidad = presupuestoUseCase.getCantidad(id);
        return ResponseEntity.ok(cantidad);
    }
}

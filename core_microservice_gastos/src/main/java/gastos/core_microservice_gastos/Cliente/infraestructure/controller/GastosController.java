package gastos.core_microservice_gastos.Cliente.infraestructure.controller;

import gastos.core_microservice_gastos.Cliente.application.port.GastosUseCase;
import gastos.core_microservice_gastos.Cliente.domain.Gasto;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.ClienteModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.GastoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.PresupuestoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.input.GastoInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador que maneja las operaciones relacionadas con los gastos.
 */
@RestController
@RequestMapping("/gastos")
public class GastosController {

    @Autowired
    private GastosUseCase gastosUseCase;

    /**
     * Método de prueba para obtener un cliente por su ID.
     *
     * @param id El ID del cliente.
     * @return La entidad ClienteModel correspondiente al ID proporcionado.
     */
    @GetMapping("/prueba")
    public ResponseEntity<ClienteModel> prueba(@RequestParam String id) {
        ClienteModel cliente = gastosUseCase.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    /**
     * Método para restar una cantidad del presupuesto de un cliente.
     *
     * @param id      El ID del cliente.
     * @param cantidad La cantidad a restar.
     * @return El modelo del presupuesto actualizado después de la resta.
     */
   /* @PostMapping("/restar")
    public ResponseEntity<PresupuestoModel> restar(@RequestParam String id, @RequestParam double cantidad) {
        PresupuestoModel presupuestoModel = gastosUseCase.restar(id, cantidad);
        return ResponseEntity.ok(presupuestoModel);
    }*/

    /**
     * Método para sumar una cantidad al presupuesto de un cliente.
     *
     * @param id      El ID del cliente.
     * @param cantidad La cantidad a sumar.
     * @return El modelo del presupuesto actualizado después de la suma.
     */
    @PostMapping("/sumar")
    public ResponseEntity<PresupuestoModel> sumar(@RequestParam String id, @RequestParam double cantidad) {
        PresupuestoModel presupuestoModel = gastosUseCase.sumar(id, cantidad);
        return ResponseEntity.ok(presupuestoModel);
    }

    /**
     * Método para buscar un presupuesto por su ID.
     *
     * @param id El ID del presupuesto.
     * @return El modelo del presupuesto correspondiente al ID proporcionado.
     */
    @GetMapping("/buscarPorId")
    public ResponseEntity<PresupuestoModel> buscarPorId(@RequestParam String id) {
        PresupuestoModel presupuestoModel = gastosUseCase.buscarPorId(id);
        return ResponseEntity.ok(presupuestoModel);
    }

    /**
     * Método para crear un nuevo gasto.
     *
     * @param gastoInput El modelo del gasto a crear.
     * @return El gasto creado.
     */
    @PostMapping("/crearGasto")
    public ResponseEntity<Gasto> crearGasto(@RequestBody GastoInput gastoInput) {
        Gasto gasto = gastosUseCase.guardarNuevoGasto(gastoInput);
        return ResponseEntity.ok(gasto);
    }

    /**
     * Método para modificar un gasto existente.
     *
     * @param gastoModel El modelo del gasto a modificar.
     * @return El gasto actualizado.
     */
    @PutMapping("/modificarGasto")
    public ResponseEntity<Gasto> modificarGasto(@RequestBody GastoModel gastoModel) {
        Gasto gastoActualizao = gastosUseCase.modificarGasto(gastoModel);
        return ResponseEntity.ok(gastoActualizao);
    }

    /**
     * Método para modificar el estado de un gasto existente.
     *
     * @param gastoModel El modelo del gasto cuya estado se va a modificar.
     * @return El gasto con el estado actualizado.
     */
    @PutMapping("/modificarEstado")
    public ResponseEntity<Gasto> modificarEstado(@RequestBody GastoModel gastoModel) {
        Gasto gastoActualizao = gastosUseCase.modificarEstado(gastoModel);
        return ResponseEntity.ok(gastoActualizao);
    }

    /**
     * Método para obtener la cantidad de dinero disponible en el presupuesto de un cliente.
     *
     * @param id El ID del cliente.
     * @return La cantidad de dinero disponible en el presupuesto.
     */
    @GetMapping("/getCantidadDinero")
    public ResponseEntity<Double> getCantidadDinero(@RequestParam String id) {
        Double cantidad = gastosUseCase.getCantidadPresupuesto(id);
        return ResponseEntity.ok(cantidad);
    }

}

package gastos.core_microservice_gastos.Cliente.infraestructure.controller;

import gastos.core_microservice_gastos.Cliente.application.port.GastosUseCase;
import gastos.core_microservice_gastos.Cliente.domain.Gasto;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.ClienteModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.GastoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.PresupuestoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.input.GastoInput;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controlador que maneja las operaciones relacionadas con los gastos.
 */
@RestController
@RequestMapping("/gastos")
@Validated
public class GastosController {

    @Autowired
    private GastosUseCase gastosUseCase;

    /**
     * Método de prueba para obtener un cliente por su ID.
     *
     * @param id El ID del cliente.
     * @return La entidad ClienteModel correspondiente al ID proporcionado.
     */
    @ApiOperation(value = "Prueba para obtener un cliente por ID")
    @ApiParam(value = "ID del cliente")
    @GetMapping("/prueba")
    public ResponseEntity<ClienteModel> prueba(@RequestParam String id) {
        ClienteModel cliente = gastosUseCase.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    /**
     * Método para sumar una cantidad al presupuesto de un cliente.
     *
     * @param id      El ID del cliente.
     * @param cantidad La cantidad a sumar.
     * @return El modelo del presupuesto actualizado después de la suma.
     */
    @ApiOperation(value = "Sumar una cantidad al presupuesto de un cliente")
    @ApiParam(value = "ID del cliente")
    @PostMapping("/sumar")
    public ResponseEntity<PresupuestoModel> sumar(
            @RequestParam String id,
            @ApiParam(value = "Cantidad a sumar") @RequestParam double cantidad) {
        PresupuestoModel presupuestoModel = gastosUseCase.sumar(id, cantidad);
        return ResponseEntity.ok(presupuestoModel);
    }

    /**
     * Método para buscar un presupuesto por su ID.
     *
     * @param id El ID del presupuesto.
     * @return El modelo del presupuesto correspondiente al ID proporcionado.
     */
    @ApiOperation(value = "Buscar un presupuesto por su ID")
    @ApiParam(value = "ID del presupuesto")
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

    @ApiOperation(value = "Crear un nuevo gasto")
    @PostMapping("/crearGasto")
    @ResponseBody
    public ResponseEntity<Gasto> crearGasto(@ApiParam(value = "Objeto para crear el nuevo gasto") @Valid @RequestBody GastoInput gastoInput, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println();

        }
        Gasto gasto = gastosUseCase.guardarNuevoGasto(gastoInput);
        return ResponseEntity.ok(gasto);

    }

    /**
     * Método para modificar un gasto existente.
     *
     * @param gastoModel El modelo del gasto a modificar.
     * @return El gasto actualizado.
     */
    @ApiOperation(value = "Modificar un gasto existente")
    @PostMapping("/modificarGasto")
    public ResponseEntity<Gasto> modificarGasto(@ApiParam(value = "Modelo del gasto a modificar") @RequestBody GastoModel gastoModel) {
        Gasto gastoActualizao = gastosUseCase.modificarGasto(gastoModel);
        return ResponseEntity.ok(gastoActualizao);
    }

    /**
     * Método para modificar el estado de un gasto existente.
     *
     * @param gastoModel El modelo del gasto cuya estado se va a modificar.
     * @return El gasto con el estado actualizado.
     */
    @ApiOperation(value = "Modificar el estado de un gasto existente")
    @ApiParam(value = "Modelo del gasto con el estado actualizado")
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
    @ApiOperation(value = "Obtener la cantidad de dinero disponible en el presupuesto de un cliente")
    @ApiParam(value = "ID del cliente")
    @GetMapping("/getCantidadDinero")
    public ResponseEntity<Double> getCantidadDinero(@RequestParam String id) {
        Double cantidad = gastosUseCase.getCantidadPresupuesto(id);
        return ResponseEntity.ok(cantidad);
    }

}

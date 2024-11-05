package gastos.core_microservice_gastos.Cliente.infraestructure.controller;


import gastos.core_microservice_gastos.Cliente.application.port.GastosUseCase;
import gastos.core_microservice_gastos.Cliente.domain.Gasto;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.ClienteModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.GastoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.PresupuestoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.output.ClientePresupuestoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastos")
public class GastosController {

    @Autowired
    private GastosUseCase gastosUseCase;



    @GetMapping("/prueba")
    public ResponseEntity<ClienteModel> prueba(@RequestParam String id) {
        ClienteModel cliente = gastosUseCase.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ClientePresupuestoDTO>> obtenerTodo() {
        List<ClientePresupuestoDTO> clientes =gastosUseCase.obtenerTodo();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping("/restar")
    public ResponseEntity<PresupuestoModel> restar(@RequestParam String id, @RequestParam double cantidad) {
        PresupuestoModel presupuestoModel= gastosUseCase.restar(id,cantidad);
        return ResponseEntity.ok(presupuestoModel);

    }

    @PostMapping("/sumar")
    public ResponseEntity<PresupuestoModel> sumar(@RequestParam String id, @RequestParam double cantidad) {
        PresupuestoModel presupuestoModel= gastosUseCase.sumar(id,cantidad);
        return ResponseEntity.ok(presupuestoModel);

    }

    @GetMapping("/buscarPorId")
    public ResponseEntity<PresupuestoModel> buscarPorId(@RequestParam String id) {
        PresupuestoModel presupuestoModel= gastosUseCase.buscarPorId(id);
        return ResponseEntity.ok(presupuestoModel);
    }

    @PostMapping("/crearGasto")
    public ResponseEntity<Gasto> crearGasto(@RequestBody GastoModel gastoModel) {

        Gasto gasto = gastosUseCase.guardarNuevoGasto(gastoModel);
        return ResponseEntity.ok(gasto);
    }


    @GetMapping("/getCantidadDinero")
    public ResponseEntity<Double> getCantidadDinero(@RequestParam String id) {
        Double cantidad=gastosUseCase.getCantidadPresupuesto(id);
        return ResponseEntity.ok(cantidad);
    }

}

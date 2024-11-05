package gastos.core_microservice_gastos.Cliente.infraestructure.controller;


import gastos.core_microservice_gastos.Cliente.application.feign.ClienteServiceClient;
import gastos.core_microservice_gastos.Cliente.application.port.GastosUseCase;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.ClienteModel;
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


}

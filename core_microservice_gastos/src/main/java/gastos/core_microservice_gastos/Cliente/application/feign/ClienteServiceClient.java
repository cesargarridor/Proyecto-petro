package gastos.core_microservice_gastos.Cliente.application.feign;

import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.ClienteModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.output.ClientePresupuestoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ClienteServiceClient",url="http://localhost:8029/clientes")
public interface ClienteServiceClient {
    @GetMapping("/findAll")
    ClientePresupuestoDTO findAll();


    @GetMapping("/buscarPorId")
    ClienteModel findById(@RequestParam(name="id") String id);



    }


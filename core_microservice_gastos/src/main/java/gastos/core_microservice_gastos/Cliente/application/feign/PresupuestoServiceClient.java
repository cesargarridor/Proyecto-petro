package gastos.core_microservice_gastos.Cliente.application.feign;

import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.PresupuestoModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="PresupuestoServiceClient",url="http://localhost:8029/presupuestos")
public interface PresupuestoServiceClient {
    @PostMapping("/restar")
    PresupuestoModel restar(@RequestParam(name="presupuestoId") String id, @RequestParam(name="cantidad")double cantidad);
    @PostMapping("/sumar")
    PresupuestoModel sumar(@RequestParam(name="presupuestoId") String id, @RequestParam(name="cantidad")double cantidad);

    @GetMapping("/buscarPorId")
    PresupuestoModel buscarPorId(@RequestParam(name="id") String id);

}

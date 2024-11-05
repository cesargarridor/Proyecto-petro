package gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.output;


import com.fasterxml.jackson.annotation.JsonInclude;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.ClienteModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.PresupuestoModel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientePresupuestoDTO {
    private ClienteModel cliente;
    private PresupuestoModel presupuesto;

}

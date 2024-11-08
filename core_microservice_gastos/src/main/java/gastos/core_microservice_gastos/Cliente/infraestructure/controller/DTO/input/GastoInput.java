 package gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.input;

import gastos.core_microservice_gastos.shared.errorHandling.validators.FormatoCheck.FormatoCheck;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GastoInput {

    @NotBlank
    private String clientId;
    @NotBlank
    private String gastoId;

    private double cantidad;
    private boolean estado;
    @NotBlank
    private String fechaCreacion;
}
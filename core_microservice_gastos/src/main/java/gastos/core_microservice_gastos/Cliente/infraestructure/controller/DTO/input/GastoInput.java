package gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.input;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GastoInput {
    private String clientId;
    private String gastoId;
    private double cantidad;
    private boolean estado;
    //private Date fechaCreacion;
}

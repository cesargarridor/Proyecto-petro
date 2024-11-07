package gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GastoModel {

    private String clientId;

    private String gastoId;

    private double cantidad;

    private boolean estado;
   // private Date fechaCreacion;
}

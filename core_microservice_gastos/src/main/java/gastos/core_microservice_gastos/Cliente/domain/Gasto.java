package gastos.core_microservice_gastos.Cliente.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.fasterxml.jackson.annotation.JsonInclude;
import gastos.core_microservice_gastos.shared.errorHandling.validators.FechaGastoCheck.FechaGastoCheckConstraint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@ApiModel(description = "Modelo para la entidad Gasto")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Gasto extends MainTable{
    public static String PATTERN_PK="clientId#";
    public static final String PATTERN_SK="clientGasto";
    public static final String ENTITY_TYPE="GASTO";

    @ApiModelProperty(notes="El id del cliente",required = true,example = "1")
    @DynamoDBAttribute(attributeName = "clientId")
    @NotBlank
    private String clientId;
    @ApiModelProperty(notes="El id del gasto",required = false,example = "1")
    @DynamoDBAttribute(attributeName = "gastoId")
    @NotBlank
    private String gastoId;
    @ApiModelProperty(notes="La cantidad que ha supuesto el gasto",required = true,example = "150")
    @DynamoDBAttribute(attributeName = "cantidad")
    @NotBlank
    private double cantidad;
    @ApiModelProperty(notes="Si el gasto esta activo o no",required = true,example = "true")
    @DynamoDBAttribute(attributeName = "estado")
    @NotBlank
    private boolean estado;

    /*@ApiModelProperty
    @DynamoDBAttribute(attributeName = "fechaCreacion")
    @FechaGastoCheckConstraint
    private Date fechaCreacion;*/

 //etiqueta que valide formato fecha y si no lanzar excepcion personalizada

}

package gastos.core_microservice_gastos.Cliente.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel(description = "Modelo para la entidad Gasto")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Gasto extends MainTable{
    public static String PATTERN_PK="clientId#";
    public static final String PATTERN_SK="clientGasto";
    public static final String ENTITY_TYPE="GASTO";

    @ApiModelProperty(notes="El id del cliente",required = true,example = "1")
    @DynamoDBAttribute(attributeName = "clientId")
    @NotBlank
    private String clientId;
    @ApiModelProperty(notes="El id del gasto",required = true,example = "1")
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

    public Gasto(String clientId) {
        super(PATTERN_PK+clientId,PATTERN_SK);
    }
    public void setPk() {
        super.setPk(PATTERN_PK + this.clientId);
    }

    public void setSk() {
        super.setSk(PATTERN_SK);
    }

    public Gasto(){
        super(PATTERN_PK,PATTERN_SK);
        this.entityType = ENTITY_TYPE;
    }

}

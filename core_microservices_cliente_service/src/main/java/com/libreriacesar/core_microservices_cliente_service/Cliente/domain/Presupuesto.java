package com.libreriacesar.core_microservices_cliente_service.Cliente.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.UUID;


@Setter
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Presupuesto extends MainTable {

    public static  String PATTERN_PK = "clientId#";
    public static final String PATTERN_SK = "presupuestoId";
    public static final String ENTITY_TYPE = "CLIENT_PRESUPUESTO";

    @ApiModelProperty(notes = "id del presupuesto del cliente", required = true, example = "B12345678")
    @DynamoDBAttribute(attributeName = "presupuestoId")
    @NotBlank
    private String presupuestoId;

    @ApiModelProperty(notes = "Cantidad de presupuesto del cliente", required = true, example = "1000")
    @DynamoDBAttribute(attributeName = "cantidad")
    @NotBlank
    private double cantidad;

    @ApiModelProperty(notes = "fecha de creacion del presupuesto", required = true)
    @DynamoDBAttribute(attributeName = "fecha_creacion")
    @NotBlank
    private Date fecha_Creacion;

    @ApiModelProperty(notes = "presupuesto activo o inactivo", required = true)
    @DynamoDBAttribute(attributeName = "enabled")
    @NotBlank
    private Boolean enabled;



}

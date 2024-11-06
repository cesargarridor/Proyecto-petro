package com.libreriacesar.core_microservices_cliente_service.Cliente.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ApiModel(description = "Modelo para la entidad Cliente")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cliente extends MainTable {

    public static  String PATTERN_PK = "clientId#";
    public static final String PATTERN_SK = "clientData";
    public static final String ENTITY_TYPE = "CLIENT";



    @ApiModelProperty(notes = "Nombre del cliente", required = true, example = "Juan Pérez")
    @DynamoDBAttribute(attributeName = "nombre")
    @NotBlank
    private String nombre;

    @ApiModelProperty(notes = "CIF del cliente", required = true, example = "B12345678")
    @DynamoDBAttribute(attributeName = "cif")
    @NotBlank
    private String cif;

    @ApiModelProperty(notes = "Teléfono del cliente", required = true, example = "+34123456789")
    @DynamoDBAttribute(attributeName = "telefono")
    @NotBlank
    private String telefono;

    @ApiModelProperty(notes = "ID del cliente", required = true, example = "12345")
    @DynamoDBAttribute(attributeName = "clientId")
    private String clientId;

    @ApiModelProperty(notes = "Correo electrónico del cliente", required = true, example = "juan.perez@ejemplo.com")
    @DynamoDBAttribute(attributeName = "email")
    private String email;

    @ApiModelProperty(notes = "Dirección del cliente", example = "Calle Falsa 123, Madrid")
    @DynamoDBAttribute(attributeName = "direccion")
    private String direccion;

    @ApiModelProperty(notes = "Estado activo del cliente", example = "true")
    @DynamoDBAttribute(attributeName = "estado")
    private boolean estado;








}

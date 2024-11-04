package com.libreriacesar.core_microservices_cliente_service.Cliente.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ApiModel(description = "Modelo para la entidad Cliente")
@DynamoDBTable(tableName = "MainTable")
public class Cliente extends MainTable {

    public static  String PATTERN_PK = "clientId#";
    public static final String PATTERN_SK = "clientData";
    public static final String ENTITY_TYPE = "CLIENT";



    @ApiModelProperty(notes = "Nombre del cliente", required = true, example = "Juan Pérez")
    @DynamoDBAttribute(attributeName = "nombre")
    @NotBlank
    private String nombre;

    @ApiModelProperty(notes = "CIF del cliente", required = true, example = "B12345678")
    @DynamoDBAttribute(attributeName = "CIF")
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





    public Cliente(String clientId) {
        super(PATTERN_PK + clientId, PATTERN_SK);
        this.clientId = clientId;
    }

    public void setPk() {
        super.setPk(PATTERN_PK + this.clientId);
    }

    public void setSk() {
        super.setSk(PATTERN_SK);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", CIF='" + cif + '\'' +
                ", telefono='" + telefono + '\'' +
                ", clientId='" + clientId + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", estado=" + estado +
                ", pk='" + getPk() + '\'' +
                ", sk='" + getSk() + '\'' +
                '}';
    }

    public Cliente(String pk, String sk, String gIndexPk, String gIndex1Pk, String gIndex1Sk, String gIndex2Pk, String gIndex3Pk, String gIndex3Sk, String gIndex4Pk, String gIndex5Pk, String gIndex6Pk, String gIndex7Pk, String gIndex7Sk, String lIndexSk, String lIndex1Sk, Date created, Date modify, String entityType, String id, String status, String clientId, String nombre, String cif, String telefono, String clientId1, String email, String direccion, boolean estado) {
        super(pk, sk, gIndexPk, gIndex1Pk, gIndex1Sk, gIndex2Pk, gIndex3Pk, gIndex3Sk, gIndex4Pk, gIndex5Pk, gIndex6Pk, gIndex7Pk, gIndex7Sk, lIndexSk, lIndex1Sk, created, modify, entityType, id, status, clientId);
        this.nombre = nombre;
        this.cif = cif;
        this.telefono = telefono;
        this.clientId = clientId1;
        this.email = email;
        this.direccion = direccion;
        this.estado = estado;
    }

    public Cliente() {
        super(PATTERN_PK + UUID.randomUUID().toString(), PATTERN_SK);
        this.id = this.getPk().split("#")[1];
        this.entityType = ENTITY_TYPE;
        this.created = new Date();

    }

    public Cliente(String nombre, String cif, String telefono, String clientId, String email, String direccion, boolean estado) {
        this.nombre = nombre;
        this.cif = cif;
        this.telefono = telefono;
        this.clientId = clientId;
        this.email = email;
        this.direccion = direccion;
        this.estado = estado;
    }

    public Cliente(MainTableBuilder<?, ?> b, String nombre, String cif, String telefono, String clientId, String email, String direccion, boolean estado) {
        super(b);
        this.nombre = nombre;
        this.cif = cif;
        this.telefono = telefono;
        this.clientId = clientId;
        this.email = email;
        this.direccion = direccion;
        this.estado = estado;
    }

    public Cliente(String pk, String sk, String nombre, String cif, String telefono, String clientId, String email, String direccion, boolean estado) {
        super(pk, sk);
        this.nombre = nombre;
        this.cif = cif;
        this.telefono = telefono;
        this.clientId = clientId;
        this.email = email;
        this.direccion = direccion;
        this.estado = estado;
    }
}

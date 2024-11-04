package com.libreriacesar.core_microservices_cliente_service.Cliente.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.UUID;


@Setter
@Getter
@ToString
public class Presupuesto extends MainTable {

    public static  String PATTERN_PK = "clientId#";
    public static final String PATTERN_SK = "presupuestoId#";
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



    public Presupuesto(String clientId) {
        super(PATTERN_PK + clientId, PATTERN_SK);
        this.presupuestoId = UUID.randomUUID().toString();  // Genera un ID único para presupuesto
        setPk();  // Establece PK usando el patrón
        setSk();
    }

    public void setPk() {
        super.setPk(PATTERN_PK + presupuestoId);  // Usa el presupuestoId para formar la PK
    }

    public void setSk() {
        super.setSk(PATTERN_SK);
    }

    public Presupuesto() {
        super(PATTERN_PK + UUID.randomUUID().toString(), PATTERN_SK);
        this.id = this.getPk().split("#")[1];
        this.entityType = ENTITY_TYPE;
        this.created = new Date();

    }

    public Presupuesto(String presupuestoId, double cantidad, Date fecha_Creacion, Boolean enabled) {
        this.presupuestoId = presupuestoId;
        this.cantidad = cantidad;
        this.fecha_Creacion = fecha_Creacion;
        this.enabled = enabled;
    }

    public Presupuesto(MainTableBuilder<?, ?> b, String presupuestoId, double cantidad, Date fecha_Creacion, Boolean enabled) {
        super(b);
        this.presupuestoId = presupuestoId;
        this.cantidad = cantidad;
        this.fecha_Creacion = fecha_Creacion;
        this.enabled = enabled;
    }

    public Presupuesto(String pk, String sk, String presupuestoId, double cantidad, Date fecha_Creacion, Boolean enabled) {
        super(pk, sk);
        this.presupuestoId = presupuestoId;
        this.cantidad = cantidad;
        this.fecha_Creacion = fecha_Creacion;
        this.enabled = enabled;
    }

    public Presupuesto(String pk, String sk, String gIndexPk, String gIndex1Pk, String gIndex1Sk, String gIndex2Pk, String gIndex3Pk, String gIndex3Sk, String gIndex4Pk, String gIndex5Pk, String gIndex6Pk, String gIndex7Pk, String gIndex7Sk, String lIndexSk, String lIndex1Sk, Date created, Date modify, String entityType, String id, String status, String clientId, String presupuestoId, double cantidad, Date fecha_Creacion, Boolean enabled) {
        super(pk, sk, gIndexPk, gIndex1Pk, gIndex1Sk, gIndex2Pk, gIndex3Pk, gIndex3Sk, gIndex4Pk, gIndex5Pk, gIndex6Pk, gIndex7Pk, gIndex7Sk, lIndexSk, lIndex1Sk, created, modify, entityType, id, status, clientId);
        this.presupuestoId = presupuestoId;
        this.cantidad = cantidad;
        this.fecha_Creacion = fecha_Creacion;
        this.enabled = enabled;
    }
}

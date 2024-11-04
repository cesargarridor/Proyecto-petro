package com.libreriacesar.core_microservices_cliente_service.Cliente.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DynamoDBTable(tableName = "MainTable")
public class MainTable {
    public static final String PK = "PK";

    public static final String SK = "SK";

    public static final String GI_PK = "GI_PK";

    public static final String GI1_PK_SK = "GI1_PK_SK";

    public static final String GI2_PK = "GI2_PK";

    public static final String GI3_PK_SK = "GI3_PK_SK";

    public static final String GI4_PK = "GI4_PK";

    public static final String GI5_PK = "GI5_PK";

    public static final String GI6_PK = "GI6_PK";

    public static final String GI7_PK_SK = "GI7_PK_SK";

    public static final String LI_SK = "LI_SK";

    public static final String LI1_SK = "LI1_SK";

    @ApiModelProperty(notes = "Partition key", required = true, allowEmptyValue = false)
    @NotBlank
    @DynamoDBHashKey(attributeName = "PK")
    private String pk;

    @ApiModelProperty(notes = "Sort key", required = true, allowEmptyValue = false)
    @NotBlank
    @DynamoDBRangeKey(attributeName = "SK")
    private String sk;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "GI_PK", attributeName = "gIndexPk")
    @ApiModelProperty(notes = "Global Index PK", required = false, allowEmptyValue = true)
    @NotBlank
    protected String gIndexPk;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "GI1_PK_SK", attributeName = "gIndex1Pk")
    @ApiModelProperty(notes = "Global Index 1 PK", required = false, allowEmptyValue = true)
    protected String gIndex1Pk;

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "GI1_PK_SK", attributeName = "gIndex1Sk")
    @ApiModelProperty(notes = "Global Index 1 SK", required = false, allowEmptyValue = true)
    protected String gIndex1Sk;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "GI2_PK", attributeName = "gIndex2Pk")
    @ApiModelProperty(notes = "Global Index 2 PK", required = false, allowEmptyValue = true)
    protected String gIndex2Pk;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "GI3_PK_SK", attributeName = "gIndex3Pk")
    @ApiModelProperty(notes = "Global Index 3 PK", required = false, allowEmptyValue = true)
    protected String gIndex3Pk;

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "GI3_PK_SK", attributeName = "gIndex3Sk")
    @ApiModelProperty(notes = "Global Index 3 SK", required = false, allowEmptyValue = true)
    protected String gIndex3Sk;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "GI4_PK", attributeName = "gIndex4Pk")
    @ApiModelProperty(notes = "Global Index 4 PK", required = false, allowEmptyValue = true)
    protected String gIndex4Pk;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "GI5_PK", attributeName = "gIndex5Pk")
    @ApiModelProperty(notes = "Global Index 5 PK", required = false, allowEmptyValue = true)
    protected String gIndex5Pk;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "GI6_PK", attributeName = "gIndex6Pk")
    @ApiModelProperty(notes = "Global Index 6 PK", required = false, allowEmptyValue = true)
    protected String gIndex6Pk;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "GI7_PK_SK", attributeName = "gIndex7Pk")
    @ApiModelProperty(notes = "Global Index 7 PK", required = false, allowEmptyValue = true)
    protected String gIndex7Pk;

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "GI7_PK_SK", attributeName = "gIndex7Sk")
    @ApiModelProperty(notes = "Global Index 7 SK", required = false, allowEmptyValue = true)
    protected String gIndex7Sk;

    @DynamoDBIndexRangeKey(localSecondaryIndexName = "LI_SK", attributeName = "lIndexSk")
    @ApiModelProperty(notes = "Local Index SK", required = false, allowEmptyValue = true)
    protected String lIndexSk;

    @DynamoDBIndexRangeKey(localSecondaryIndexName = "LI1_SK", attributeName = "lIndex1Sk")
    @ApiModelProperty(notes = "Local Index 1 SK", required = false, allowEmptyValue = true)
    protected String lIndex1Sk;

    @DynamoDBAttribute(attributeName = "created")
    @ApiModelProperty(notes = "Fecha de creación", required = false, allowEmptyValue = true, example = "")
    protected Date created;

    @DynamoDBAttribute(attributeName = "modify")
    @ApiModelProperty(notes = "Fecha de modificación", required = false, allowEmptyValue = true, example = "")
    protected Date modify;

    @DynamoDBAttribute(attributeName = "entityType")
    @ApiModelProperty(notes = "Tipo de entidad", required = false, allowEmptyValue = true, example = "")
    protected String entityType;

    @DynamoDBAttribute(attributeName = "id")
    @ApiModelProperty(notes = "Identificador", required = false, allowEmptyValue = true, example = "")
    protected String id;

    @DynamoDBAttribute(attributeName = "status")
    @ApiModelProperty(notes = "Estado", required = false, allowEmptyValue = true, example = "")
    protected String status;

    @DynamoDBAttribute(attributeName = "clientId")
    @ApiModelProperty(notes = "Identificador del cliente", required = false, allowEmptyValue = true, example = "")
    protected String clientId;

/**
 * Instantiates a new main table.
 *
 * @param pk the pk
 * @param sk the sk
 */
public MainTable(@NotBlank String pk, @NotBlank String sk) {
    super();
    this.pk = pk;
    this.sk = sk;
}

/**
 * Gets the pk.
 *
 * @return the pk
 */
public String getPk() {
    return pk;
}

/**
 * Sets the pk.
 *
 * @param pk the new pk
 */
public void setPk(String pk) {
    this.pk = pk;
}

/**
 * Gets the sk.
 *
 * @return the sk
 */
public String getSk() {
    return sk;
}

/**
 * Sets the sk.
 *
 * @param sk the new sk
 */
public void setSk(String sk) {
    this.sk = sk;
}

/**
 * Gets the g index pk.
 *
 * @return the g index pk
 */
public String getgIndexPk() {
    return gIndexPk;
}

/**
 * Sets the g index pk.
 *
 * @param gIndexPk the new g index pk
 */
public void setgIndexPk(String gIndexPk) {
    this.gIndexPk = gIndexPk;
}

/**
 * Gets the g index 1 pk.
 *
 * @return the g index 1 pk
 */
public String getgIndex1Pk() {
    return gIndex1Pk;
}

/**
 * Sets the g index 1 pk.
 *
 * @param gIndex1Pk the new g index 1 pk
 */
public void setgIndex1Pk(String gIndex1Pk) {
    this.gIndex1Pk = gIndex1Pk;
}

/**
 * Gets the g index 1 sk.
 *
 * @return the g index 1 sk
 */
public String getgIndex1Sk() {
    return gIndex1Sk;
}

/**
 * Sets the g index 1 sk.
 *
 * @param gIndex1Sk the new g index 1 sk
 */
public void setgIndex1Sk(String gIndex1Sk) {
    this.gIndex1Sk = gIndex1Sk;
}

/**
 * Gets the g index 2 pk.
 *
 * @return the g index 2 pk
 */
public String getgIndex2Pk() {
    return gIndex2Pk;
}

/**
 * Sets the g index 2 pk.
 *
 * @param gIndex2Pk the new g index 2 pk
 */
public void setgIndex2Pk(String gIndex2Pk) {
    this.gIndex2Pk = gIndex2Pk;
}

/**
 * Gets the g index 3 pk.
 *
 * @return the g index 3 pk
 */
public String getgIndex3Pk() {
    return gIndex3Pk;
}

/**
 * Sets the g index 3 pk.
 *
 * @param gIndex3Pk the new g index 3 pk
 */
public void setgIndex3Pk(String gIndex3Pk) {
    this.gIndex3Pk = gIndex3Pk;
}

/**
 * Gets the g index 3 sk.
 *
 * @return the g index 3 sk
 */
public String getgIndex3Sk() {
    return gIndex3Sk;
}

/**
 * Sets the g index 3 sk.
 *
 * @param gIndex3Sk the new g index 3 sk
 */
public void setgIndex3Sk(String gIndex3Sk) {
    this.gIndex3Sk = gIndex3Sk;
}

/**
 * Gets the g index 4 pk.
 *
 * @return the g index 4 pk
 */
public String getgIndex4Pk() {
    return gIndex4Pk;
}

/**
 * Sets the g index 4 pk.
 *
 * @param gIndex4Pk the new g index 4 pk
 */
public void setgIndex4Pk(String gIndex4Pk) {
    this.gIndex4Pk = gIndex4Pk;
}

/**
 * Gets the g index 5 pk.
 *
 * @return the g index 5 pk
 */
public String getgIndex5Pk() {
    return gIndex5Pk;
}

/**
 * Sets the g index 5 pk.
 *
 * @param gIndex5Pk the new g index 5 pk
 */
public void setgIndex5Pk(String gIndex5Pk) {
    this.gIndex5Pk = gIndex5Pk;
}

/**
 * Gets the g index 6 pk.
 *
 * @return the g index 6 pk
 */
public String getgIndex6Pk() {
    return gIndex6Pk;
}

/**
 * Sets the g index 6 pk.
 *
 * @param gIndex6Pk the new g index 6 pk
 */
public void setgIndex6Pk(String gIndex6Pk) {
    this.gIndex6Pk = gIndex6Pk;
}

/**
 * Gets the l index sk.
 *
 * @return the l index sk
 */
public String getlIndexSk() {
    return lIndexSk;
}

/**
 * Sets the l index sk.
 *
 * @param lIndexSk the new l index sk
 */
public void setlIndexSk(String lIndexSk) {
    this.lIndexSk = lIndexSk;
}

/**
 * Gets the l index 1 sk.
 *
 * @return the l index 1 sk
 */
public String getlIndex1Sk() {
    return lIndex1Sk;
}

/**
 * Sets the l index 1 sk.
 *
 * @param lIndex1Sk the new l index 1 sk
 */
public void setlIndex1Sk(String lIndex1Sk) {
    this.lIndex1Sk = lIndex1Sk;
}

/**
 * Gets the created.
 *
 * @return the created
 */
public Date getCreated() {
    return created;
}

/**
 * Sets the created.
 *
 * @param created the new created
 */
public void setCreated(Date created) {
    this.created = created;
}

/**
 * Gets the modify.
 *
 * @return the modify
 */
public Date getModify() {
    return modify;
}

/**
 * Sets the modify.
 *
 * @param modify the new modify
 */
public void setModify(Date modify) {
    this.modify = modify;
}

/**
 * Gets the entity type.
 *
 * @return the entity type
 */
public String getEntityType() {
    return entityType;
}

/**
 * Sets the entity type.
 *
 * @param entityType the new entity type
 */
public void setEntityType(String entityType) {
    this.entityType = entityType;
}

/**
 * Gets the id.
 *
 * @return the id
 */
public String getId() {
    return id;
}

/**
 * Sets the id.
 *
 * @param id the new id
 */
public void setId(String id) {
    this.id = id;
}

/**
 * Gets the status.
 *
 * @return the status
 */
public String getStatus() {
    return status;
}

/**
 * Sets the status.
 *
 * @param status the new status
 */
public void setStatus(String status) {
    this.status = status;
}

/**
 * Gets the g index 7 pk.
 *
 * @return the g index 7 pk
 */
public String getgIndex7Pk() {
    return gIndex7Pk;
}

/**
 * Sets the g index 7 pk.
 *
 * @param gIndex7Pk the new g index 7 pk
 */
public void setgIndex7Pk(String gIndex7Pk) {
    this.gIndex7Pk = gIndex7Pk;
}

/**
 * Gets the g index 7 sk.
 *
 * @return the g index 7 sk
 */
public String getgIndex7Sk() {
    return gIndex7Sk;
}

/**
 * Sets the g index 7 sk.
 *
 * @param gIndex7Sk the new g index 7 sk
 */
public void setgIndex7Sk(String gIndex7Sk) {
    this.gIndex7Sk = gIndex7Sk;
}

/**
 * Gets the client id.
 *
 * @return the client id
 */
public String getClientId() {
    return clientId;
}

/**
 * Sets the client id.
 *
 * @param clientId the new client id
 */
public void setClientId(String clientId) {
    this.clientId = clientId;
}

/**
 * To string.
 *
 * @return the string
 */
@Override
public String toString() {
    return "MainTable [pk=" + pk + ", sk=" + sk + ", gIndexPk=" + gIndexPk + ", gIndex1Pk=" + gIndex1Pk
            + ", gIndex1Sk=" + gIndex1Sk + ", gIndex2Pk=" + gIndex2Pk + ", gIndex3Pk=" + gIndex3Pk + ", gIndex3Sk="
            + gIndex3Sk + ", gIndex4Pk=" + gIndex4Pk + ", gIndex5Pk=" + gIndex5Pk + ", gIndex6Pk=" + gIndex6Pk
            + ", gIndex7Pk=" + gIndex7Pk + ", gIndex7Sk=" + gIndex7Sk + ", lIndexSk=" + lIndexSk + ", lIndex1Sk="
            + lIndex1Sk + ", created=" + created + ", modify=" + modify + ", entityType=" + entityType + ", id="
            + id + ", status=" + status + ", clientId=" + clientId + "]";
}

}

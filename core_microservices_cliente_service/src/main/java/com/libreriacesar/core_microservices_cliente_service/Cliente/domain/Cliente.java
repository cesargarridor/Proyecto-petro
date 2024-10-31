package com.libreriacesar.core_microservices_cliente_service.Cliente.domain;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName="Cliente")
public class Cliente {
    @DynamoDBHashKey
    private String id; // ID del cliente

    @DynamoDBAttribute
    private String nombre; // Nombre del cliente

    @DynamoDBAttribute
    private String email; // Correo electrónico del cliente

    @DynamoDBAttribute
    private String telefono; // Teléfono del cliente

    @DynamoDBAttribute
    private String direccion; // Dirección del cliente

    @DynamoDBAttribute
    private boolean estado; // Estado del cliente (activo/inactivo)

    @DynamoDBAttribute
    private String CIF;
}

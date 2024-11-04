package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClienteModel {
    private String clientId;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private boolean estado;
    private String CIF;
}

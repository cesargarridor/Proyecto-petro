package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.output;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClienteSalida {
    private String clientId;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private boolean estado;
    private String cif;
}

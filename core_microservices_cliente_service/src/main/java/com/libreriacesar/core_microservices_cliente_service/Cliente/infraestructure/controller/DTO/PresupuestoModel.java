package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PresupuestoModel {
    private String presupuestoId;
    private double cantidad;
    private boolean enabled;
    private Date fecha_Creacion;
}

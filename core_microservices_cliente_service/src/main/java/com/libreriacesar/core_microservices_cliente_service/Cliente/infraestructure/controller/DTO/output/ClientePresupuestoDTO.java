package com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.output;


import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Presupuesto;
import jakarta.websocket.server.ServerEndpoint;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientePresupuestoDTO {
    private Cliente cliente;
    private Presupuesto presupuesto;

}

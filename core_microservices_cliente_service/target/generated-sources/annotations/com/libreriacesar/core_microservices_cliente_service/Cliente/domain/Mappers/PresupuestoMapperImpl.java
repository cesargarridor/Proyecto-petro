package com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Mappers;

import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Presupuesto;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.PresupuestoModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-07T12:02:52+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class PresupuestoMapperImpl implements PresupuestoMapper {

    @Override
    public Presupuesto modelToEntity(PresupuestoModel presupuestoModel) {
        if ( presupuestoModel == null ) {
            return null;
        }

        Presupuesto.PresupuestoBuilder<?, ?> presupuesto = Presupuesto.builder();

        presupuesto.cantidad( presupuestoModel.getCantidad() );
        presupuesto.clientId( presupuestoModel.getClientId() );
        presupuesto.presupuestoId( presupuestoModel.getPresupuestoId() );
        presupuesto.fecha_Creacion( presupuestoModel.getFecha_Creacion() );
        presupuesto.enabled( presupuestoModel.isEnabled() );

        presupuesto.sk( "presupuestoId" );

        return presupuesto.build();
    }
}

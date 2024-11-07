package com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Mappers;

import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Presupuesto;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.PresupuestoModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-07T09:21:22+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class PresupuestoMapperImpl implements PresupuestoMapper {

    @Override
    public Presupuesto modelToEntity(PresupuestoModel presupuestoModel) {
        if ( presupuestoModel == null ) {
            return null;
        }

        Presupuesto presupuesto = new Presupuesto();

        presupuesto.setPk( presupuestoModel.getClientId() );
        presupuesto.setSk( "presupuestoId" );

        return presupuesto;
    }
}

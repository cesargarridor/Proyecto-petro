package com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Mappers;

import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Presupuesto;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.ClienteModel;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.PresupuestoModel;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.output.ClienteSalida;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PresupuestoMapper {
    PresupuestoMapper INSTANCE= Mappers.getMapper(PresupuestoMapper.class);

    @Mapping(target = "sk",constant = Presupuesto.PATTERN_SK)
    @Mapping(target="cantidad",source="cantidad")
    Presupuesto modelToEntity(PresupuestoModel presupuestoModel);



}

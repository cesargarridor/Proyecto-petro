package com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Mappers;

import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.ClienteModel;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.output.ClienteSalida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {
    ClienteMapper INSTANCE= Mappers.getMapper(ClienteMapper.class);

    ClienteSalida clienteToClienteSalida(Cliente cliente);

    @Mapping(target = "pk", expression = "java(clienteModel.getClientId())")
    @Mapping(target = "sk", constant = Cliente.PATTERN_SK)
    @Mapping(target="clientId",expression = "java(Cliente.PATTERN_PK + clienteModel.getClientId())")
    @Mapping(target = "id", source = "clientId")
    @Mapping(target = "gIndexPk", source = "nombre")
    @Mapping(target = "gIndex2Pk", source = "cif")
    @Mapping(target = "gIndex3Pk", source = "telefono")
    Cliente modelToEntity(ClienteModel clienteModel);

}

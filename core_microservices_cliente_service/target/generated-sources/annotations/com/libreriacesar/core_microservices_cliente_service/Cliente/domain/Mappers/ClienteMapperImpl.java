package com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Mappers;

import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.ClienteModel;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.output.ClienteSalida;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-07T09:21:24+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteSalida clienteToClienteSalida(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteSalida clienteSalida = new ClienteSalida();

        return clienteSalida;
    }

    @Override
    public Cliente modelToEntity(ClienteModel clienteModel) {
        if ( clienteModel == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setPk( clienteModel.getClientId() );
        cliente.setSk( "clientData" );
        cliente.setClientId( Cliente.PATTERN_PK + clienteModel.getClientId() );

        return cliente;
    }
}

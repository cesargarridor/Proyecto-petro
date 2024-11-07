package com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Mappers;

import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.ClienteModel;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.output.ClienteSalida;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-07T10:05:42+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteSalida clienteToClienteSalida(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteSalida clienteSalida = new ClienteSalida();

        clienteSalida.setClientId( cliente.getClientId() );
        clienteSalida.setNombre( cliente.getNombre() );
        clienteSalida.setEmail( cliente.getEmail() );
        clienteSalida.setTelefono( cliente.getTelefono() );
        clienteSalida.setDireccion( cliente.getDireccion() );
        clienteSalida.setEstado( cliente.isEstado() );
        clienteSalida.setCif( cliente.getCif() );

        return clienteSalida;
    }

    @Override
    public Cliente modelToEntity(ClienteModel clienteModel) {
        if ( clienteModel == null ) {
            return null;
        }

        Cliente.ClienteBuilder<?, ?> cliente = Cliente.builder();

        cliente.id( clienteModel.getClientId() );
        cliente.gIndexPk( clienteModel.getNombre() );
        cliente.gIndex2Pk( clienteModel.getCif() );
        cliente.gIndex3Pk( clienteModel.getTelefono() );
        cliente.nombre( clienteModel.getNombre() );
        cliente.cif( clienteModel.getCif() );
        cliente.telefono( clienteModel.getTelefono() );
        cliente.email( clienteModel.getEmail() );
        cliente.direccion( clienteModel.getDireccion() );
        cliente.estado( clienteModel.isEstado() );

        cliente.pk( clienteModel.getClientId() );
        cliente.sk( "clientData" );
        cliente.clientId( Cliente.PATTERN_PK + clienteModel.getClientId() );

        return cliente.build();
    }
}

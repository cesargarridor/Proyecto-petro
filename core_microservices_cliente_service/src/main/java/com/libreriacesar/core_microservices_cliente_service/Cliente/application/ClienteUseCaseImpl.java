package com.libreriacesar.core_microservices_cliente_service.Cliente.application;

import com.libreriacesar.core_microservices_cliente_service.Cliente.application.port.ClientUseCase;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.ClienteModel;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository.port.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteUseCaseImpl implements ClientUseCase {

    private final ClienteRepository clienteRepository;


    public ClienteUseCaseImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente createCliente(ClienteModel clienteModel) {
        Cliente cliente = new Cliente();

        cliente.setPk(clienteModel.getClientId());
        cliente.setSk();
        cliente.setgIndexPk(clienteModel.getNombre());
        cliente.setgIndex2Pk(clienteModel.getCif());
        cliente.setgIndex3Pk(clienteModel.getTelefono());

        cliente.setClientId(clienteModel.getClientId());
        cliente.setNombre(clienteModel.getNombre());
        cliente.setEmail(clienteModel.getEmail());
        cliente.setTelefono(clienteModel.getTelefono());
        cliente.setDireccion(clienteModel.getDireccion());
        cliente.setEstado(clienteModel.isEstado());
        cliente.setCif(clienteModel.getCif());

        clienteRepository.save(cliente);

        return cliente;
    }

    @Override
    public Cliente getClienteById(String id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public void deleteCliente(String id) {
        Cliente cliente = getClienteById(id);
        if (cliente != null) {
            clienteRepository.delete(cliente);
        } else {
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
    }
    @Override
    public void updateCliente(ClienteModel clienteModel) {
        Cliente cliente = getClienteById(clienteModel.getClientId());
        if (cliente != null) {
            cliente.setgIndexPk(clienteModel.getNombre());
            cliente.setgIndex2Pk(clienteModel.getCif());
            cliente.setgIndex3Pk(clienteModel.getTelefono());

            cliente.setEmail(clienteModel.getEmail());
            cliente.setTelefono(clienteModel.getTelefono());
            cliente.setDireccion(clienteModel.getDireccion());
            cliente.setEstado(clienteModel.isEstado());
            cliente.setCif(clienteModel.getCif());


            clienteRepository.save(cliente);
        } else {
            throw new RuntimeException("Cliente no encontrado con ID: " + clienteModel.getClientId());
        }
    }

}

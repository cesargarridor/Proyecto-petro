package com.libreriacesar.core_microservices_cliente_service.Cliente.application;

import com.libreriacesar.core_microservices_cliente_service.Cliente.application.port.ClientUseCase;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Cliente;
import com.libreriacesar.core_microservices_cliente_service.Cliente.domain.Presupuesto;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.ClienteModel;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.controller.DTO.output.ClientePresupuestoDTO;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository.port.ClienteRepository;
import com.libreriacesar.core_microservices_cliente_service.Cliente.infraestructure.repository.port.PresupuestoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteUseCaseImpl implements ClientUseCase {

    private final ClienteRepository clienteRepository;

    private PresupuestoRepository presupuestoRepository;

    public ClienteUseCaseImpl(ClienteRepository clienteRepository, PresupuestoRepository presupuestoRepository) {
        this.clienteRepository = clienteRepository;
        this.presupuestoRepository = presupuestoRepository;
    }

    @Override
    public Cliente createCliente(ClienteModel clienteModel) {
        Optional<Cliente> clienteExistente = Optional.ofNullable(clienteRepository.findById(clienteModel.getClientId()));

        if (clienteExistente.isPresent()) {
            throw new RuntimeException("El cliente con ID " + clienteModel.getClientId() + " ya existe.");
        }

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

        if (clienteModel.getPresupuesto() != null) {
            Presupuesto presupuesto = new Presupuesto(cliente.getClientId());
            presupuesto.setPk(cliente.getClientId());
            presupuesto.setSk();
            presupuesto.setCantidad(clienteModel.getPresupuesto().getCantidad());
            presupuesto.setEnabled(clienteModel.getPresupuesto().isEnabled());
            presupuesto.setFecha_Creacion(clienteModel.getPresupuesto().getFecha_Creacion());

            presupuestoRepository.save(presupuesto);
        }

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

    @Override
    public List<Cliente> findAllClientesBySk(String skValue) {
        return clienteRepository.findAllClientesBySk(skValue);
    }

    @Override
    public ClientePresupuestoDTO findClienteAndPresupuestoByClientId(String clientId) {
        return clienteRepository.findClienteAndPresupuestoByClientId(clientId);
    }


}

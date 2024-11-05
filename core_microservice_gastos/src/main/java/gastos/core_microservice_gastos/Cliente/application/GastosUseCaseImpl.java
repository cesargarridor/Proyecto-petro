package gastos.core_microservice_gastos.Cliente.application;

import gastos.core_microservice_gastos.Cliente.application.feign.ClienteServiceClient;
import gastos.core_microservice_gastos.Cliente.application.port.GastosUseCase;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.ClienteModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.output.ClientePresupuestoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GastosUseCaseImpl implements GastosUseCase {

    private final ClienteServiceClient clienteServiceClient;

    @Autowired
    public GastosUseCaseImpl(ClienteServiceClient clienteServiceClient) {
        this.clienteServiceClient = clienteServiceClient;
    }

    @Override
    public ClienteModel obtenerClientePorId(String id) {
        return clienteServiceClient.findById(id);
    }

    @Override
    public List<ClientePresupuestoDTO> obtenerTodo() {
        return (List<ClientePresupuestoDTO>) clienteServiceClient.findAll();
    }
}

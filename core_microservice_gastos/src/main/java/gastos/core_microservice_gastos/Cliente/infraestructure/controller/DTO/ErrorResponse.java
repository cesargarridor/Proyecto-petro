package gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String errorCode;
    private HttpStatus status;
}


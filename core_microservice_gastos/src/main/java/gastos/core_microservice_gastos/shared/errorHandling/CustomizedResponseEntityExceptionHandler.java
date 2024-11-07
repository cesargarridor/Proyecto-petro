package gastos.core_microservice_gastos.shared.errorHandling;

import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.ErrorResponse;
import gastos.core_microservice_gastos.shared.errorHandling.exceptions.GenericErrorException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(GenericErrorException.class)
    public final ResponseEntity<ErrorResponse> FormatoFechaException(
            GenericErrorException ex, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMsgError(),
                ex.getErrorCode() != null ? ex.getErrorCode().toString() : "Error en el formato de la fecha",
                ex.getHttpStatus()
        );



        return new ResponseEntity<>(errorResponse, new HttpHeaders(), ex.getHttpStatus());
    }
}

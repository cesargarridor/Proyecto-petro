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
    public final ResponseEntity<ErrorResponse> handleGenericErrorException(
            GenericErrorException ex, WebRequest request) {

        String errorCode = (ex.getErrorCode() != null) ? ex.getErrorCode().toString() : "Error detectado";
        String msgError = ex.getMsgError() != null ? ex.getMsgError() : "Ocurrió un error inesperado";

        ErrorResponse errorResponse = new ErrorResponse(msgError, errorCode, ex.getHttpStatus());

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), ex.getHttpStatus());
    }
}
package gastos.core_microservice_gastos.shared.errorHandling;

import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.ErrorResponse;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.ReturnGenericModel;
import gastos.core_microservice_gastos.shared.errorHandling.exceptions.GenericErrorException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Metodo que devuelve la excepcion
     *
     * @param e ConstraintViolationException
     * @return String mensaje de error
     */
    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ReturnGenericModel<String>> handleResourceNotFoundException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            strBuilder.append(" | "+ violation.getPropertyPath() + " - " + violation.getMessage());
        }
        return new ResponseEntity<ReturnGenericModel<String>>(
                new ReturnGenericModel<String>(null, strBuilder.toString()),
                HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(GenericErrorException.class)
    public final ResponseEntity<ErrorResponse> handleGenericErrorException(
            GenericErrorException ex, WebRequest request) {

        String errorCode = (ex.getErrorCode() != null) ? ex.getErrorCode().toString() : "Error detectado";
        String msgError = ex.getMsgError() != null ? ex.getMsgError() : "Ocurrió un error inesperado";

        ErrorResponse errorResponse = new ErrorResponse(msgError, errorCode, ex.getHttpStatus());

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), ex.getHttpStatus());
    }
}


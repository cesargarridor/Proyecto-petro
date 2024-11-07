package gastos.core_microservice_gastos.shared.errorHandling.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GenericErrorException extends RuntimeException{
    private String msgError;
    private HttpStatus httpStatus;
    private Enum errorCode;

    public GenericErrorException(String msgError, HttpStatus httpStatus) {
        this.msgError = msgError;
        this.httpStatus = httpStatus;
    }

    public GenericErrorException(Enum errorCode, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public GenericErrorException(Enum errorCode, String msgError, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.msgError = msgError;
        this.httpStatus = httpStatus;
    }
}

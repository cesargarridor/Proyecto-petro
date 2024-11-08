package gastos.core_microservice_gastos.shared.errorHandling.validators.FechaGastoCheck;

import gastos.core_microservice_gastos.shared.errorHandling.exceptions.GenericErrorException;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FechaGastoCheck implements ConstraintValidator<FechaGastoCheckConstraint, String> {

    @Override
    public void initialize(FechaGastoCheckConstraint date) {
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        if (date == null) {
            return false;
        }

        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new GenericErrorException(
                    "Formato de fecha inválido, debe ser: yyyy-MM-dd",
                    HttpStatus.BAD_REQUEST
            );
        }

        return true;
    }
}

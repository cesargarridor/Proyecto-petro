package gastos.core_microservice_gastos.shared.errorHandling.validators.FechaGastoCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class FechaGastoCheck implements ConstraintValidator<FechaGastoCheckConstraint, String> {

    @Override
    public void initialize(FechaGastoCheckConstraint date) {
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        return date == null || date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}

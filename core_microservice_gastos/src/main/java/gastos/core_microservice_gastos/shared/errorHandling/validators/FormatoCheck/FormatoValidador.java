package gastos.core_microservice_gastos.shared.errorHandling.validators.FormatoCheck;

import gastos.core_microservice_gastos.shared.errorHandling.exceptions.GenericErrorException;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FormatoValidador implements ConstraintValidator<FormatoCheck, String> {


    @Override
    public void initialize(FormatoCheck date) {
    }

    /*@Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        if (date == null) {
            return false;
        }

        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
           return false;
        }

        return true;
    }*/
    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println();
        return date != null && date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}

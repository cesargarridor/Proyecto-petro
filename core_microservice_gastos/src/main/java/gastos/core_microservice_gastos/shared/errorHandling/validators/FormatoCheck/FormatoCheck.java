package gastos.core_microservice_gastos.shared.errorHandling.validators.FormatoCheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = FormatoValidador.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormatoCheck {
    String message() default "El formato de la fecha es incorrecto. Debe ser 'yyyy-MM-dd'";
    //String pattern() default "yyyy-MM-dd";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

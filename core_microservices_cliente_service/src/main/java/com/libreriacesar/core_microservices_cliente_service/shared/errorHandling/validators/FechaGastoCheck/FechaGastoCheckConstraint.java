package com.libreriacesar.core_microservices_cliente_service.shared.errorHandling.validators.FechaGastoCheck;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FechaGastoCheck.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface FechaGastoCheckConstraint {
    String message() default "Formato de fecha invalido, tiene que ser: yyyy-MM-dd";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

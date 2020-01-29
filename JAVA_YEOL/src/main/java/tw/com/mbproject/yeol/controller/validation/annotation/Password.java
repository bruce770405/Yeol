package tw.com.mbproject.yeol.controller.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import tw.com.mbproject.yeol.controller.validation.validator.EmailValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { EmailValidator.class })
@ReportAsSingleViolation
public @interface Password {
    
    String message() default "Incorrect password format";
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

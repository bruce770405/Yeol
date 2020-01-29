package tw.com.mbproject.yeol.controller.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import tw.com.mbproject.yeol.controller.validation.validator.MemberNameValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { MemberNameValidator.class })
@ReportAsSingleViolation
public @interface MemberName {

    String message() default "Incorrect member name format";
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

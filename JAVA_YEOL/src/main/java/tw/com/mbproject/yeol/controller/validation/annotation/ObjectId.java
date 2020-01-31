package tw.com.mbproject.yeol.controller.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import tw.com.mbproject.yeol.controller.validation.validator.ObjectIdValidator;

/** ID驗證 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ObjectIdValidator.class })
@ReportAsSingleViolation
public @interface ObjectId {
    
    String message() default "Incorrect id format";
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

package tw.com.mbproject.yeol.controller.validation.validator;

import tw.com.mbproject.yeol.controller.validation.FormatRegex;
import tw.com.mbproject.yeol.controller.validation.annotation.Email;
import tw.com.mbproject.yeol.exception.YeolException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String>{

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (FormatRegex.EMAIL_FORMAT.isValid(email)) {
            return true;
        }
        throw new YeolException(FormatRegex.EMAIL_FORMAT.getErrCode());
    }

}

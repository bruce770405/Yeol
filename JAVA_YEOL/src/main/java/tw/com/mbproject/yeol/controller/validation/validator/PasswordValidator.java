package tw.com.mbproject.yeol.controller.validation.validator;

import tw.com.mbproject.yeol.controller.validation.FormatRegex;
import tw.com.mbproject.yeol.controller.validation.annotation.Password;
import tw.com.mbproject.yeol.exception.YeolException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String>{

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (FormatRegex.PASSWORD_FORMAT.isValid(password)) {
            return true;
        }
        throw new YeolException(FormatRegex.PASSWORD_FORMAT.getErrCode());
    }

}

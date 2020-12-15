package tw.com.mbproject.yeol.controller.validation.validator;

import tw.com.mbproject.yeol.controller.validation.FormatRegex;
import tw.com.mbproject.yeol.controller.validation.annotation.MemberName;
import tw.com.mbproject.yeol.exception.YeolException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MemberNameValidator  implements ConstraintValidator<MemberName, String> {

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (FormatRegex.MEMBER_NAME_FORMAT.isValid(name)) {
            return true;
        }
        throw new YeolException(FormatRegex.MEMBER_NAME_FORMAT.getErrCode());
    }

}

package tw.com.mbproject.yeol.controller.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.controller.validation.FormatRegex;
import tw.com.mbproject.yeol.controller.validation.annotation.ObjectId;
import tw.com.mbproject.yeol.exception.YeolException;

/** ID驗證器 */
public class ObjectIdValidator implements ConstraintValidator<ObjectId, String> {

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        if (FormatRegex.ID_FORMAT.isValid(id)) {
            return true;
        }
        throw new YeolException(ErrCode.INCORRECT_ID_FORMAT);
    }

}

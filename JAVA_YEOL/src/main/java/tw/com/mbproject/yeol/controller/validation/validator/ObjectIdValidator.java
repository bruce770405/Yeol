package tw.com.mbproject.yeol.controller.validation.validator;

import tw.com.mbproject.yeol.controller.validation.FormatRegex;
import tw.com.mbproject.yeol.controller.validation.annotation.ObjectId;
import tw.com.mbproject.yeol.exception.YeolException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/** ID驗證器 */
public class ObjectIdValidator implements ConstraintValidator<ObjectId, String> {

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        if (FormatRegex.ID_FORMAT.isValid(id)) {
            return true;
        }
        throw new YeolException(FormatRegex.ID_FORMAT.getErrCode());
    }

}

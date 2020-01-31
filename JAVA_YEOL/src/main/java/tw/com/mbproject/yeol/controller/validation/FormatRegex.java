package tw.com.mbproject.yeol.controller.validation;

import tw.com.mbproject.yeol.controller.response.code.ErrCode;

import java.util.regex.Pattern;

/** 格式正則列舉 */
public enum FormatRegex {
    
    /** 
     * MongoDB ObjectId預設為英數字組成長度24個字
     */
    ID_FORMAT("^[a-fA-F0-9]{24}$",
            ErrCode.INCORRECT_FORMAT),
    
    /**
     * Accept letter, digit, at least 3 to 12 characters
     */
    MEMBER_NAME_FORMAT("^\\w{3,12}$",
            ErrCode.INCORRECT_MEMBER_NAME_FORMAT),
    /**
     * Email format, that is xxx@xxx.xxx(.xxx)
     */
    EMAIL_FORMAT("^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$",
            ErrCode.INCORRECT_EMAIL_FORMAT),
    /**
     * Require at least one letter, one digit and at least 8 characters.
     */
    PASSWORD_FORMAT("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            ErrCode.INCORRECT_PASSWORD_FORMAT)
    
    ;
    
    private String regex;
    private ErrCode errCode;

    FormatRegex(String regex, ErrCode errCode) {
        this.regex = regex;
        this.errCode = errCode;
    }
    
    public boolean isValid(String value) {
        return Pattern.compile(regex).matcher(value).find();
    }
    public boolean isNotValid(String value) {
        return !isValid(value);
    }

    public ErrCode getErrCode() {
        return errCode;
    }

}

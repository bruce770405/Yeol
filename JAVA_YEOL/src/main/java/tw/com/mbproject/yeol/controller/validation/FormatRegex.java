package tw.com.mbproject.yeol.controller.validation;

import java.util.regex.Pattern;

public enum FormatRegex {
    
    ID_FORMAT("^[a-fA-F0-9]{24}$"),
    
    /**
     * Accept letter, digit, at least 3 to 12 characters
     */
    MEMBER_NAME_FORMAT("^\\w{3,12}$"),
    /**
     * Email format, that is xxx@xxx.xxx(.xxx)
     */
    MEMBER_EMAIL_FORMAT("^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$"),
    /**
     * Require at least one letter, one digit and at least 8 characters.
     */
    MEMBER_PASSWORD_FORMAT("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    
    ;
    
    private String regex;
    
    private FormatRegex(String regex) {
        this.regex = regex;
    }
    
    public boolean isValid(String value) {
        return Pattern.compile(regex).matcher(value).find();
    }
    public boolean isNotValid(String value) {
        return !isValid(value);
    }
    
}

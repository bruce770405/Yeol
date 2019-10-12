package tw.com.mbproject.yeol.controller.response.code;

public enum ErrCode {
    
    SUCCESS("S0000", "Success"),
    FAIL("F0000", "Fail"),
    
    INCORRECT_FORMAT("F0001", "Incorrect format"),
    INCORRECT_MEMBER_NAME_FORMAT("F0002", "Incorrect member name format"),
    INCORRECT_MEMBER_EMAIL_FORMAT("F0003", "Incorrect member email format"),
    INCORRECT_MEMBER_PASSWORD_FORMAT("F0004", "Incorrect member password format"),
    
    DATA_EXISTED("E0000", "Data existed"),
    MEMBER_EXISTED("E0001", "Member existed"),
    
    
    RESPONSE_NO_ERROR_CODE("R0000", "Response code and messages is required");
    
    private String code;
    private String msg;
    
    ErrCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    
}

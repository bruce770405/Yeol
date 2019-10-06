package tw.com.mbproject.yeol.controller.response.code;

public enum ErrCode {
    
    SUCCESS("S0000", "Success"),
    
    
    
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

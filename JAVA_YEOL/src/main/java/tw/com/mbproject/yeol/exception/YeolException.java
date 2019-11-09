package tw.com.mbproject.yeol.exception;

import lombok.Getter;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;

public class YeolException extends Exception {
    private static final long serialVersionUID = 1L;

    @Getter
    private ErrCode errCode;
    
    public YeolException(ErrCode errCode) {
        this.errCode = errCode;
    }

}

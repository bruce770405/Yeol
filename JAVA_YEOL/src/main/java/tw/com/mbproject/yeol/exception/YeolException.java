package tw.com.mbproject.yeol.exception;

import lombok.Getter;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;

public class YeolException extends RuntimeException {

    @Getter
    private final ErrCode errCode;

    public YeolException(ErrCode errCode) {
        this.errCode = errCode;
    }

}

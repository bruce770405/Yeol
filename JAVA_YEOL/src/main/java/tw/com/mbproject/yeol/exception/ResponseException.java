package tw.com.mbproject.yeol.exception;

import tw.com.mbproject.yeol.controller.response.code.ErrCode;

public class ResponseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResponseException(ErrCode errCode) {
        super(errCode.getMsg());
    }

}

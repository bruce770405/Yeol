package tw.com.mbproject.yeol.controller.response;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.exception.ResponseException;


public class BaseResponse {
    
    @Getter
    private String code;
    
    @Getter
    private String msg;
    
    public BaseResponse(ErrCode errCode) {
        if (StringUtils.isAnyBlank(errCode.getCode(), errCode.getMsg())) {
            throw new ResponseException(ErrCode.RESPONSE_NO_ERROR_CODE);
        }
        this.code = errCode.getCode();
        this.msg = errCode.getMsg();
    }

}

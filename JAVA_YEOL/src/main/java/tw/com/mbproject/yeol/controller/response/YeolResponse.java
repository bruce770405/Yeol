package tw.com.mbproject.yeol.controller.response;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.exception.ResponseException;

public class YeolResponse<T> {
    
    @Getter
    private String code;
    
    @Getter
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value="data")
    private T data;
    
    public YeolResponse() {
        this.code = ErrCode.SUCCESS.getCode();
        this.msg = ErrCode.SUCCESS.getMsg();
    }
    
    public YeolResponse(ErrCode errCode) {
        if (StringUtils.isAnyBlank(errCode.getCode(), errCode.getMsg())) {
            throw new ResponseException(ErrCode.RESPONSE_NO_ERROR_CODE);
        }
        this.code = errCode.getCode();
        this.msg = errCode.getMsg();
    }
    
    public YeolResponse(T data, ErrCode errCode) {
        this.code = errCode.getCode();
        this.msg = errCode.getMsg();
        this.data = data;
    }
    
}

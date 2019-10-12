package tw.com.mbproject.yeol.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import tw.com.mbproject.yeol.common.response.BaseResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;

public class YeolResponse<T> extends BaseResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value="data")
    private T data;
    
    public YeolResponse() {
        super(ErrCode.SUCCESS);
    }
    
    public YeolResponse(ErrCode errCode) {
        super(errCode);
    }
    
    public YeolResponse(T data, ErrCode errCode) {
        super(errCode);
        this.data = data;
    }
    
}

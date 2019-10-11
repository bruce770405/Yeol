package tw.com.mbproject.yeol.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import tw.com.mbproject.yeol.common.response.BaseResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;

public class YeolResponse<T> extends BaseResponse {

    @JsonProperty(value="data")
    private T data;
    
    public YeolResponse() {
        super(ErrCode.SUCCESS);
    }
    
    public YeolResponse(T data, ErrCode errCode) {
        super(errCode);
        this.data = data;
    }
    
}

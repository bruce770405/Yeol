package tw.com.mbproject.yeol.controller.response;

import tw.com.mbproject.yeol.controller.response.code.ErrCode;

public interface ResponseBuilder<T> {
    
    
    T build(ErrCode errCode);

}

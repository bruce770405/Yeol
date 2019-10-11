package tw.com.mbproject.yeol.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import tw.com.mbproject.yeol.common.response.BaseResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.MessageDto;

public class CreateMessageResponse extends BaseResponse {

    @JsonProperty(value="messages")
    @Getter @Setter
    private MessageDto message;
   
    private CreateMessageResponse(CreateMessageResponse.Builder builder, ErrCode errCode) {
        super(errCode);
        this.message = builder.message;
    }
    
    public static final class Builder implements ResponseBuilder<CreateMessageResponse>{
        
        private MessageDto message;
        
        public CreateMessageResponse.Builder message(MessageDto message) {
            this.message = message;
            return this;
        }
        
        @Override
        public CreateMessageResponse build(ErrCode errCode) {
            return new CreateMessageResponse(this, errCode);
        }
        
    }

}

package tw.com.mbproject.yeol.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import tw.com.mbproject.yeol.common.response.BaseResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.MessageDto;

public class MessageResponse extends BaseResponse {

    @JsonProperty(value="message")
    @Getter @Setter
    private MessageDto message;
    
    private MessageResponse(MessageResponse.Builder builder, ErrCode errCode) {
        super(errCode);
        this.message = builder.message;
    }

    public static Builder builder() {
        return new Builder();
    }
    
    public static final class Builder implements ResponseBuilder<MessageResponse>{
        
        private MessageDto message;
        
        public MessageResponse.Builder message(MessageDto message) {
            this.message = message;
            return this;
        }
        
        @Override
        public MessageResponse build(ErrCode errCode) {
            return new MessageResponse(this, errCode);
        }
        
    }
}

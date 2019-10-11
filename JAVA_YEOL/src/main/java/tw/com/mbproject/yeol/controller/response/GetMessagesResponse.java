package tw.com.mbproject.yeol.controller.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import tw.com.mbproject.yeol.common.response.BaseResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.MessageDto;

public class GetMessagesResponse extends BaseResponse {

    @JsonProperty(value="messages")
    @Getter @Setter
    private List<MessageDto> messages;
   
    private GetMessagesResponse(GetMessagesResponse.Builder builder, ErrCode errCode) {
        super(errCode);
        this.messages = builder.messages;
    }
    
    public static final class Builder implements ResponseBuilder<GetMessagesResponse>{
        
        private List<MessageDto> messages;
        
        public GetMessagesResponse.Builder messages(List<MessageDto> messages) {
            this.messages = messages;
            return this;
        }
        
        @Override
        public GetMessagesResponse build(ErrCode errCode) {
            return new GetMessagesResponse(this, errCode);
        }
        
    }

}

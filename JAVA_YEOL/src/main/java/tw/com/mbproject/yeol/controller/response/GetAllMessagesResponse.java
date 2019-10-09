package tw.com.mbproject.yeol.controller.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import tw.com.mbproject.yeol.common.response.BaseResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.MessageDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAllMessagesResponse extends BaseResponse {

    @JsonProperty(value="messages")
    @Getter @Setter
    private List<MessageDto> messages;
   
    private GetAllMessagesResponse(GetAllMessagesResponse.Builder builder, ErrCode errCode) {
        super(errCode);
        this.messages = builder.messages;
    }
    
    public static final class Builder implements ResponseBuilder<GetAllMessagesResponse>{
        
        private List<MessageDto> messages;
        
        public GetAllMessagesResponse.Builder messages(List<MessageDto> messages) {
            this.messages = messages;
            return this;
        }
        
        @Override
        public GetAllMessagesResponse build(ErrCode errCode) {
            return new GetAllMessagesResponse(this, errCode);
        }
        
    }

}

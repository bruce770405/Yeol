package tw.com.mbproject.yeol.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import tw.com.mbproject.yeol.controller.validation.annotation.ObjectId;

@Data
public class QueryMemberRequest {
    
    @JsonProperty(value="id")
    private String id;
    @JsonProperty(value="email")
    private String email;

}

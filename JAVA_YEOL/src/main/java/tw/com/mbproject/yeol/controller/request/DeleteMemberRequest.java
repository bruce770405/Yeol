package tw.com.mbproject.yeol.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DeleteMemberRequest {
    
    @JsonProperty(value="id")
    private String id;
    
}
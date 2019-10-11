package tw.com.mbproject.yeol.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreateMessageRequest {
    
    @JsonProperty(value="title")
    private String title;
    
    @JsonProperty(value="content")
    private String content;

}

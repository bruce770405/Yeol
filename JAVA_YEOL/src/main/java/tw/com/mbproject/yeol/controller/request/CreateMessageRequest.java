package tw.com.mbproject.yeol.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class CreateMessageRequest {
    
    @JsonProperty(value="title")
    @Getter @Setter
    private String title;
    
    @JsonProperty(value="content")
    @Getter @Setter
    private String content;

}

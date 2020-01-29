package tw.com.mbproject.yeol.controller.request;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreateMessageRequest {
    
    @JsonProperty(value="title")
    @NotEmpty
    private String title;
    
    @JsonProperty(value="content")
    private String content;

}

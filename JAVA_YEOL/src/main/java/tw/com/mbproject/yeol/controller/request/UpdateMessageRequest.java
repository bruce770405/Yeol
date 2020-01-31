package tw.com.mbproject.yeol.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import tw.com.mbproject.yeol.controller.validation.annotation.ObjectId;

@Data
public class UpdateMessageRequest {
    
    @JsonProperty(value="id")
    @ObjectId
    private String id;
    
    @JsonProperty(value="title")
    private String title;
    
    @JsonProperty(value="content")
    private String content;
    
    @JsonProperty(value="view")
    private Integer view;
    
    @JsonProperty(value="up")
    private Integer up;
    
    @JsonProperty(value="down")
    private Integer down;
}

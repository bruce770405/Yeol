package tw.com.mbproject.yeol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class MemberDto {
    
    @JsonProperty(value="id")
    @Getter @Setter
    private String id;
    
    @JsonProperty(value="email")
    @Getter @Setter
    private String email;
    
    @JsonProperty(value="postNumber")
    @Getter @Setter
    private Integer postNumber;
}

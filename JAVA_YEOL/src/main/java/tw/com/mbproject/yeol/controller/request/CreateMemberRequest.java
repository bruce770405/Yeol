package tw.com.mbproject.yeol.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import tw.com.mbproject.yeol.controller.validation.annotation.Email;

@Data
public class CreateMemberRequest {
    
    @JsonProperty(value="name")
    private String name;
    
    @JsonProperty(value="password")
    private String password;
    
    @JsonProperty(value="email")
    @Email
    private String email;

}

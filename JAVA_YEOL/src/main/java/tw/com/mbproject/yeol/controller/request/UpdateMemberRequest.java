package tw.com.mbproject.yeol.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateMemberRequest {

    @JsonProperty(value="password")
    private String password;
    
    @JsonProperty(value="email")
    private String email;
}

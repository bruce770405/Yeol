package tw.com.mbproject.yeol.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import tw.com.mbproject.yeol.controller.validation.annotation.MemberName;
import tw.com.mbproject.yeol.controller.validation.annotation.Password;

@Data
public class LoginMemberRequest {

    @JsonProperty(value = "name")
    @MemberName
    private String name;

    @JsonProperty(value = "password")
    @Password
    private String password;

}

package tw.com.mbproject.yeol.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import tw.com.mbproject.yeol.controller.validation.annotation.MemberName;
import tw.com.mbproject.yeol.controller.validation.annotation.Password;

import javax.validation.constraints.NotBlank;

@Data
public class LoginMemberRequest {

    @JsonProperty(value = "name")
    @MemberName
    private String name;

    @JsonProperty(value = "password")
    @NotBlank
    private String password;

}

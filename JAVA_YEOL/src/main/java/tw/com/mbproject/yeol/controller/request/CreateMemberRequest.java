package tw.com.mbproject.yeol.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import tw.com.mbproject.yeol.controller.validation.annotation.Email;
import tw.com.mbproject.yeol.controller.validation.annotation.MemberName;
import tw.com.mbproject.yeol.controller.validation.annotation.Password;

@Data
public class CreateMemberRequest {

    @JsonProperty(value = "name")
    @MemberName
    private String name;

    @JsonProperty(value = "password")
    @Password
    private String password;

    @JsonProperty(value = "email")
    @Email
    private String email;

}

package tw.com.mbproject.yeol.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import tw.com.mbproject.yeol.controller.validation.annotation.Email;
import tw.com.mbproject.yeol.controller.validation.annotation.ObjectId;
import tw.com.mbproject.yeol.controller.validation.annotation.Password;

@Data
public class UpdateMemberRequest {

    @JsonProperty(value = "id")
    @ObjectId
    private String id;

    @JsonProperty(value = "password")
    @Password
    private String password;

    @JsonProperty(value = "email")
    @Email
    private String email;

    @JsonProperty(value = "postNumber")
    private Integer postNumber;
}

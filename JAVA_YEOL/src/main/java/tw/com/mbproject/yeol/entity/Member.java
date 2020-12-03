package tw.com.mbproject.yeol.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import tw.com.mbproject.yeol.constant.Role;
import tw.com.mbproject.yeol.entity.name.BaseName;
import tw.com.mbproject.yeol.entity.name.MembersName;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder()
@Document(collection = MembersName.COLLECTION)
public class Member extends Base {

    @Id
    private String id;
    @Field(MembersName.FIELD_NAME)
    private String name;
    @Field(MembersName.FIELD_PASSWORD)
    private String password;
    @Field(MembersName.FIELD_EMAIL)
    private String email;
    @Field(MembersName.FIELD_POST_NUMBER)
    private Integer postNumber;
    @Field(MembersName.FIELD_ROLES)
    private Collection<String> roles;
    @Field(BaseName.FIELD_DELETE_FLAG)
    protected Boolean deleteFlag;

    public UserDetails toUserDetails() {
        if (CollectionUtils.isEmpty(roles)) {
            roles = Stream.of(Role.USER.name()).collect(Collectors.toSet());
        }
        return User.withUsername(name).password(password)
                .roles(roles.toArray(new String[roles.size()])).build();
    }

}

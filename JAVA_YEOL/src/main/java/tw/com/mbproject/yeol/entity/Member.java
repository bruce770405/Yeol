package tw.com.mbproject.yeol.entity;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tw.com.mbproject.yeol.entity.name.MembersName;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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
    
    
    public UserDetails toUserDetails() {
        if (CollectionUtils.isEmpty(roles)) {
            roles = new HashSet<>();
            roles.add("USER");
        }
        return User.withUsername(name).password(password)
                .roles(roles.toArray(new String[roles.size()])).build();
    }

}

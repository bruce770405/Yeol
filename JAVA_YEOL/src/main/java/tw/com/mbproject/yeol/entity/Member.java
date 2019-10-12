package tw.com.mbproject.yeol.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tw.com.mbproject.yeol.entity.name.MembersName;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}

package tw.com.mbproject.yeol.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "members")
public class Member extends Base {
    
    @Id
    private String id;
    @Field("name")
    private String name;
    @Field("password")
    private String password;
    @Field("email")
    private String email;
    @Field("postNumber")
    private Integer postNumber;

}

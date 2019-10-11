package tw.com.mbproject.yeol.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "members")
public class Member {
    
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
    @Field("createMs")
    private Long createMs;
    @Field("updateMs")
    private Long updateMs;
    @Field("deleteFlag")
    private Boolean deleteFlag;

}

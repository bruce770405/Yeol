package tw.com.mbproject.yeol.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tw.com.mbproject.yeol.entity.name.MessagesName;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = MessagesName.COLLECTION)
@SuperBuilder
public class Message extends Base {

    @Id
    private String id;
    @Field(MessagesName.FIELD_MEMBER_ID)
    private String memberId;
    @Field(MessagesName.FIELD_MEMBER_NAME)
    private String memberName;
    @Field(MessagesName.FIELD_TITLE)
    private String title;
    @Field(MessagesName.FIELD_CONTENT)
    private String content;
    @Field(MessagesName.FIELD_VIEW)
    private Integer view;
    @Field(MessagesName.FIELD_UP)
    private Integer up;
    @Field(MessagesName.FIELD_DOWN)
    private Integer down;
    
}

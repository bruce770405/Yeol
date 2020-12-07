package tw.com.mbproject.yeol.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import tw.com.mbproject.yeol.entity.name.BaseName;
import tw.com.mbproject.yeol.entity.name.MessagesName;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@Document(collection = MessagesName.COLLECTION)
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
    @Field(BaseName.FIELD_DELETE_FLAG)
    protected Boolean deleteFlag;
}

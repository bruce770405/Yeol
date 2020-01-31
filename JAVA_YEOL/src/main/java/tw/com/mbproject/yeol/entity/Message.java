package tw.com.mbproject.yeol.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.experimental.SuperBuilder;
import tw.com.mbproject.yeol.entity.name.MessagesName;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
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

    @Builder
    public Message(
            String id,
            String memberId,
            String memberName,
            String title,
            String content,
            Integer view,
            Integer up,
            Integer down,
            Long createMs,
            Long updateMs,
            Boolean deleteFlag)
    {
        super(createMs, updateMs, deleteFlag);
        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.title = title;
        this.content = content;
        this.view = view;
        this.up = up;
        this.down = down;
    }
    
}

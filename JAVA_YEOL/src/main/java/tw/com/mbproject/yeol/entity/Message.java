package tw.com.mbproject.yeol.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
    
    public static Builder builder() {
        return new Message.Builder();
    }
    
    private Message(Message.Builder builder) {
        this.id = builder.id;
        this.memberId = builder.memberId;
        this.memberName = builder.memberName;
        this.title = builder.title;
        this.content = builder.content;
        this.view = builder.view;
        this.up = builder.up;
        this.down = builder.down;
        this.createMs = builder.createMs;
        this.updateMs = builder.updateMs;
        this.deleteFlag = builder.deleteFlag;
    }
    
    public static final class Builder {
        
        private String id;
        private String memberId;
        private String memberName;
        private String title;
        private String content;
        private Integer view;
        private Integer up;
        private Integer down;
        private Long createMs;
        private Long updateMs;
        private Boolean deleteFlag;
        
        public Message.Builder id(String id) {
            this.id = id;
            return this;
        }
        
        public Message.Builder memberId(String memberId) {
            this.memberId = memberId;
            return this;
        }
        
        public Message.Builder memberName(String memberName) {
            this.memberName = memberName;
            return this;
        }
        
        public Message.Builder title(String title) {
            this.title = title;
            return this;
        }
        
        public Message.Builder content(String content) {
            this.content = content;
            return this;
        }
        
        public Message.Builder view(Integer view) {
            this.view = view;
            return this;
        }
        
        public Message.Builder up(Integer up) {
            this.up = up;
            return this;
        }
        
        public Message.Builder down(Integer down) {
            this.down = down;
            return this;
        }
        
        public Message.Builder createMs(Long createMs) {
            this.createMs = createMs;
            return this;
        }
        
        public Message.Builder updateMs(Long updateMs) {
            this.updateMs = updateMs;
            return this;
        }
        
        public Message.Builder deleteFlag(Boolean deleteFlag) {
            this.deleteFlag = deleteFlag;
            return this;
        }
        
        public Message build() {
            return new Message(this);
        }
        
    }
    
}

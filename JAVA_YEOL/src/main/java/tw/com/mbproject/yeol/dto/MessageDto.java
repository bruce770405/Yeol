package tw.com.mbproject.yeol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import tw.com.mbproject.yeol.entity.Message;

public class MessageDto {
    
    @JsonProperty(value="id")
    @Getter @Setter
    private String id;
    
    @JsonProperty(value="authorId")
    @Getter @Setter
    private String authorId;
    
    @JsonProperty(value="authorName")
    @Getter @Setter
    private String authorName;
    
    @JsonProperty(value="title")
    @Getter @Setter
    private String title;
    
    @JsonProperty(value="content")
    @Getter @Setter
    private String content;
    
    @JsonProperty(value="views")
    @Getter @Setter
    private Integer views;
    
    @JsonProperty(value="up")
    @Getter @Setter
    private Integer up;
    
    @JsonProperty(value="down")
    @Getter @Setter
    private Integer down;
    
    @JsonProperty(value="createMs")
    @Getter @Setter
    private Long createMs;
    
    @JsonProperty(value="updateMs")
    @Getter @Setter
    private Long updateMs;
    
    @JsonProperty(value="deleteFlag")
    @Getter @Setter
    private Boolean deleteFlag;
    
    public static MessageDto valueOf(Message e) {
        return new MessageDto.Builder()
                .id(e.getId())
                .authorId(e.getAuthorId())
                .authorName(e.getAuthorName())
                .title(e.getTitle())
                .content(e.getContent())
                .views(e.getViews()).up(e.getUp())
                .down(e.getDown()).createMs(e.getCreateMs())
                .updateMs(e.getUpdateMs()).deleteFlag(e.getDeleteFlag())
                .build();
    }

    private MessageDto(MessageDto.Builder builder) {
        this.id = builder.id;
        this.authorId = builder.authorId;
        this.authorName = builder.authorName;
        this.title = builder.title;
        this.content = builder.content;
        this.views = builder.views;
        this.up = builder.up;
        this.down = builder.down;
        this.createMs = builder.createMs;
        this.updateMs = builder.updateMs;
        this.deleteFlag = builder.deleteFlag;
    }
    
    public static final class Builder {
        
        private String id;
        private String authorId;
        private String authorName;
        private String title;
        private String content;
        private Integer views;
        private Integer up;
        private Integer down;
        private Long createMs;
        private Long updateMs;
        private Boolean deleteFlag;
        
        public MessageDto.Builder id(String id) {
            this.id = id;
            return this;
        }
        
        public MessageDto.Builder authorId(String authorId) {
            this.authorId = authorId;
            return this;
        }
        
        public MessageDto.Builder authorName(String authorName) {
            this.authorName = authorName;
            return this;
        }
        
        public MessageDto.Builder title(String title) {
            this.title = title;
            return this;
        }
        
        public MessageDto.Builder content(String content) {
            this.content = content;
            return this;
        }
        
        public MessageDto.Builder views(Integer views) {
            this.views = views;
            return this;
        }
        
        public MessageDto.Builder up(Integer up) {
            this.up = up;
            return this;
        }
        
        public MessageDto.Builder down(Integer down) {
            this.down = down;
            return this;
        }
        
        public MessageDto.Builder createMs(Long createMs) {
            this.createMs = createMs;
            return this;
        }
        
        public MessageDto.Builder updateMs(Long updateMs) {
            this.updateMs = updateMs;
            return this;
        }
        
        public MessageDto.Builder deleteFlag(Boolean deleteFlag) {
            this.deleteFlag = deleteFlag;
            return this;
        }
        
        public MessageDto build() {
            return new MessageDto(this);
        }
        
    }
}

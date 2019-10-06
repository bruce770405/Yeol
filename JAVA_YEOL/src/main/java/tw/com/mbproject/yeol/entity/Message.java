package tw.com.mbproject.yeol.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "messages")
public class Message {

    @Id
    private String id;
    @Field("authorId")
    private String authorId;
    @Field("authorName")
    private String authorName;
    @Field("title")
    private String title;
    @Field("content")
    private String content;
    @Field("views")
    private Integer views;
    @Field("up")
    private Integer up;
    @Field("down")
    private Integer down;
    @Field("createMs")
    private Long createMs;
    @Field("updateMs")
    private Long updateMs;
    @Field("deleteFlag")
    private Boolean deleteFlag;
    
}

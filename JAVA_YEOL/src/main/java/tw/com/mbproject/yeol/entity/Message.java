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
    public String id;
    @Field("authorId")
    public String authorId;
    @Field("authorName")
    public String authorName;
    @Field("title")
    public String title;
    @Field("content")
    public String content;
    @Field("views")
    public Integer views;
    @Field("up")
    public Integer up;
    @Field("down")
    public Integer down;
    @Field("createMs")
    public Long createMs;
    @Field("updateMs")
    public Long updateMs;
    @Field("deleteFlag")
    public Boolean deleteFlag;
    
}

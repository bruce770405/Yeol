package tw.com.mbproject.yeol.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "messages")
public class Message {

    @Id
    public String id;
    
    @Field("title")
    public String title;
    @Field("content")
    public String content;
    @Field("views")
    public Integer views;

    public Message() {
    }

    public Message(String title) {
        this.title = title;
    }
    
}

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
@Document(collection = "messages")
public class Message {

    @Id
    private String id;
    @Field("memberId")
    private String memberId;
    @Field("memberName")
    private String memberName;
    @Field("title")
    private String title;
    @Field("content")
    private String content;
    @Field("views")
    private Integer view;
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

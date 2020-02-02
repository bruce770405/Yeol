package tw.com.mbproject.yeol.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tw.com.mbproject.yeol.entity.Message;
import tw.com.mbproject.yeol.util.YeolDateUtil;

@Builder
public class MessageDto {
    
    @JsonProperty(value="id")
    @Getter @Setter
    private String id;
    
    @JsonProperty(value="memberId")
    @Getter @Setter
    private String memberId;
    
    @JsonProperty(value="memberName")
    @Getter @Setter
    private String memberName;
    
    @JsonProperty(value="title")
    @Getter @Setter
    private String title;
    
    @JsonProperty(value="content")
    @Getter @Setter
    private String content;
    
    @JsonProperty(value="view")
    @Getter @Setter
    private Integer view;
    
    @JsonProperty(value="up")
    @Getter @Setter
    private Integer up;
    
    @JsonProperty(value="down")
    @Getter @Setter
    private Integer down;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonProperty(value="createDateTime")
    @Getter @Setter
    private Date createDateTime;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonProperty(value="updateDateTime")
    @Getter @Setter
    private Date updateDateTime;
    
    @JsonProperty(value="deleteFlag")
    @Getter @Setter
    private Boolean deleteFlag;
    
    public static MessageDto valueOf(Message e) {
        return new MessageDto.MessageDtoBuilder()
                .id(e.getId())
                .memberId(e.getMemberId())
                .memberName(e.getMemberName())
                .title(e.getTitle())
                .content(e.getContent())
                .view(e.getView())
                .up(e.getUp())
                .down(e.getDown())
                .createDateTime(YeolDateUtil.getDate(e.getCreateMs()))
                .updateDateTime(YeolDateUtil.getDate(e.getUpdateMs()))
                .deleteFlag(e.getDeleteFlag())
                .build();
    }

}

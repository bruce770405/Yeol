package tw.com.mbproject.yeol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tw.com.mbproject.yeol.entity.Member;

@Builder
public class MemberDto {
    
    @JsonProperty(value="id")
    @Getter @Setter
    private String id;
    
    @JsonProperty(value="name")
    @Getter @Setter
    private String name;
    
    @JsonProperty(value="email")
    @Getter @Setter
    private String email;
    
    @JsonProperty(value="postNumber")
    @Getter @Setter
    private Integer postNumber;
    
    public static MemberDto valueOf(Member e) {
        return MemberDto.builder()
                .id(e.getId())
                .name(e.getName())
                .email(e.getEmail())
                .postNumber(e.getPostNumber())
                .build();
    }
}

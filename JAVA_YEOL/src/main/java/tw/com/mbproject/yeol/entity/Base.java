package tw.com.mbproject.yeol.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Base {
    
    @Field("createMs")
    protected Long createMs;
    @Field("updateMs")
    protected Long updateMs;
    @Field("deleteFlag")
    protected Boolean deleteFlag;

}

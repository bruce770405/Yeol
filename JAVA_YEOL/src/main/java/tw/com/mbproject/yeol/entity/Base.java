package tw.com.mbproject.yeol.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import tw.com.mbproject.yeol.entity.name.BaseName;

@Data
public class Base {

    @Field(BaseName.FIELD_CREATE_MS)
    protected Long createMs;
    @Field(BaseName.FIELD_UPDATE_MS)
    protected Long updateMs;

}

package tw.com.mbproject.yeol.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tw.com.mbproject.yeol.entity.name.BaseName;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Base {

    @Field(BaseName.FIELD_CREATE_MS)
    protected Long createMs;
    @Field(BaseName.FIELD_UPDATE_MS)
    protected Long updateMs;
    @Field(BaseName.FIELD_DELETE_FLAG)
    protected Boolean deleteFlag;

}

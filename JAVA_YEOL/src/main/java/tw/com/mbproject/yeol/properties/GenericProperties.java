package tw.com.mbproject.yeol.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GenericProperties {
    
    public static int PAGE_SIZE;
    
    @Value("${yeol.message.page.size}")
    public void setPageSize(int pageSize) {
        GenericProperties.PAGE_SIZE = pageSize;
    }

}

package tw.com.mbproject.yeol.controller.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegexTests {
    
    @Test
    public void idFormat() {
        
        boolean result = FormatRegex.ID_FORMAT.isValid("507f1f77bcf86cd799439011");
        Assertions.assertTrue(result);
        
    }

}

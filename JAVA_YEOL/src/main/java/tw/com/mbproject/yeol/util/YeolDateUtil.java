package tw.com.mbproject.yeol.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class YeolDateUtil {
    
    private final static ZoneId DEFAULT_ZONE_ID = ZoneId.of("Asia/Taipei");
    
    public static Long getCurrentMillis() {
        return LocalDateTime.now().atZone(DEFAULT_ZONE_ID).toInstant().toEpochMilli();
    }
    
    public static Long getYesterdayMillis() {
        return LocalDateTime.now().minusDays(1).atZone(DEFAULT_ZONE_ID).toInstant().toEpochMilli();
    }
    
    public static Long getLastYearMillis() {
        return LocalDateTime.now().minusYears(1).atZone(DEFAULT_ZONE_ID).toInstant().toEpochMilli();
    }
    
    public static Date getDate(Long millisecond) {
        if (millisecond != null) {
            return new Date(millisecond);
        }
        return null;
    }
    
}

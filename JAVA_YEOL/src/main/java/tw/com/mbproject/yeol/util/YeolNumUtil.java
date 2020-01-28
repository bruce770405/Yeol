package tw.com.mbproject.yeol.util;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * 擴充NumberUtils工具.
 */
public final class YeolNumUtil extends NumberUtils {
    
    private YeolNumUtil() {}
    
    /**
     * 取得整數位數<br>
     * e.g. if value = 1000 then return 4.
     * @param value
     * @return
     */
    public final static int getDigitNumber(int value) {
        return (int) (Math.log10(value) + 1);
    }

}

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
     * @param value 整數
     * @return 位數
     */
    public static int getDigitNumber(int value) {
        return (int) (Math.log10(value) + 1);
    }

}

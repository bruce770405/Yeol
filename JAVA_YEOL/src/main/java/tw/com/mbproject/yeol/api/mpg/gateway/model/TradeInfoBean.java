package tw.com.mbproject.yeol.api.mpg.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TradeInfoBean {
    /** 藍新金流商店代號. */
    @JsonProperty("MerchantID")
    private String merchantId;

    /** JSON 或是 String. */
    @JsonProperty("RespondType")
    private String respondType;

    /** 時間戳記 ex: 1400137200. */
    @JsonProperty("TimeStamp")
    private String timeStamp;
    @JsonProperty("Version")
    private String version;
    @JsonProperty("LangType")
    private String langType;
    @JsonProperty("MerchantOrderNo")
    private String merchantOrderNo;
    @JsonProperty("Amt")
    private Integer amt;
    @JsonProperty("ItemDesc")
    private String itemDesc;
    @JsonProperty("TradeLimit")
    private String tradeLimit;
    @JsonProperty("ExpireDate")
    private String expireDate;
    @JsonProperty("ReturnURL")
    private String returnUrl;
    @JsonProperty("NotifyURL")
    private String notifyUrl;
    @JsonProperty("CustomerURL")
    private String customerUrl;
    @JsonProperty("ClientBackURL")
    private String clientBackUrl;
}

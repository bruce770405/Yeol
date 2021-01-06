package tw.com.mbproject.yeol.api.mpg.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Pay {
    @JsonProperty("MerchantID")
    private String merchantId;
    @JsonProperty("TradeInfo")
    private String tradeInfo;
    @JsonProperty("TradeSha")
    private String tradeSha;
    @JsonProperty("Version")
    private String version = "1.5";
}

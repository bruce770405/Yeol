package tw.com.mbproject.yeol.controller.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletSaveRequest {
    private BigDecimal coin;
}

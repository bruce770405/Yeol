package tw.com.mbproject.yeol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.WalletSaveRequest;
import tw.com.mbproject.yeol.controller.response.YeolResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.service.WalletService;

@RestController
@RequestMapping(value = "/api/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping(value = "/save")
    public Mono<YeolResponse<?>> saveCoin(WalletSaveRequest request) {
        return walletService.saveCoin(request).map(walletSaveDto -> new YeolResponse<>(walletSaveDto, ErrCode.SUCCESS));
    }

    @PostMapping(value = "/get")
    public Mono<YeolResponse<?>> getWallet(WalletSaveRequest request) {
        return walletService.saveCoin(request).map(walletSaveDto -> new YeolResponse<>(walletSaveDto, ErrCode.SUCCESS));
    }
}

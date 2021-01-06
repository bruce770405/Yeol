package tw.com.mbproject.yeol.service.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.WalletSaveRequest;
import tw.com.mbproject.yeol.dto.WalletSaveDto;
import tw.com.mbproject.yeol.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService {

    @Override
    public Mono<WalletSaveDto> saveCoin(WalletSaveRequest walletSaveRequest) {
        WalletSaveDto dto = new WalletSaveDto();

        return Mono.just(dto);
    }
}

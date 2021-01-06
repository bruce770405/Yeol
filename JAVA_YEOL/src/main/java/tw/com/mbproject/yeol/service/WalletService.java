package tw.com.mbproject.yeol.service;

import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.WalletSaveRequest;
import tw.com.mbproject.yeol.dto.WalletSaveDto;

public interface WalletService {

    Mono<WalletSaveDto> saveCoin(WalletSaveRequest walletSaveRequest);
}

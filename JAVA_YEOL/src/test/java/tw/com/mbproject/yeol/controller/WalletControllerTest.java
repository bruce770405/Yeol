package tw.com.mbproject.yeol.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.request.WalletSaveRequest;
import tw.com.mbproject.yeol.controller.response.YeolResponse;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.WalletSaveDto;
import tw.com.mbproject.yeol.service.WalletService;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

@WebFluxTest(value = WalletController.class)
@ExtendWith(SpringExtension.class)
public class WalletControllerTest {

    private final static String API = "/api/wallet/";

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private WalletService walletService;

    @Test
    @DisplayName("test wallet save one coin success")
    @WithUserDetails
    void testWalletSaveCoin() {

        WalletSaveDto dto = new WalletSaveDto();
        dto.setMessageId("mock_message_id");
        doReturn(Mono.just(dto)).when(walletService).saveCoin(any(WalletSaveRequest.class));

        WalletSaveRequest request = new WalletSaveRequest();
        request.setCoin(BigDecimal.ONE);
        webTestClient.mutateWith(csrf()).post()
                .uri(API.concat("save")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), WalletSaveRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(YeolResponse.class)
                .value(response -> response.getCode().equals(ErrCode.SUCCESS.getCode()));

    }
}

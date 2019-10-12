package tw.com.mbproject.yeol.controller.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.response.YeolResponse;
import tw.com.mbproject.yeol.exception.YeolException;

@RestControllerAdvice
public class GlobalExceptionHandler  {
    
    @ExceptionHandler({ YeolException.class })
    public Mono<YeolResponse<String>> handleYeolException(YeolException e) {
        
        var response = new YeolResponse<String>(e.getErrCode());
        
        return Mono.just(response);
    }

}

package tw.com.mbproject.yeol.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import reactor.core.publisher.Mono;
import tw.com.mbproject.yeol.controller.response.YeolResponse;
import tw.com.mbproject.yeol.exception.YeolException;

@RestControllerAdvice
public class GlobalExceptionHandler  {
    
    @ExceptionHandler({ YeolException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<YeolResponse<String>> handleYeolException(YeolException e) {
        
        var response = new YeolResponse<String>(e.getErrCode());
        
        return Mono.just(response);
    }

}

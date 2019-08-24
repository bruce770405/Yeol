package tw.com.mbproject.yeol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tw.com.mbproject.yeol.interceptor.AuthenInterceptor;

@Configuration
public class BeanConfig {

    @Bean
    public AuthenInterceptor loginInterceptor() {
        return new AuthenInterceptor();
    }
}

package tw.com.mbproject.yeol.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import tw.com.mbproject.yeol.interceptor.AuthenInterceptor;

@Configuration
@EnableWebMvc
public class InterceptorWebMvcConfig implements WebMvcConfigurer {
    
    @Autowired
    private AuthenInterceptor authenInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenInterceptor).addPathPatterns("/api/**");
    }

}

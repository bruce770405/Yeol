package tw.com.mbproject.yeol.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import tw.com.mbproject.yeol.interceptor.AuthenInterceptor;

@EnableWebFlux
@Configuration
@ComponentScan(basePackageClasses = {AuthenInterceptor.class})
public class WebConfig implements WebFluxConfigurer {

    /**
     * 設定允許存取一般的資源.
     *
     * @param registry the ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//        registry.addResourceHandler("/**.html", "/images/**").addResourceLocations("/");
    }


    /**
     * 跨域請求設定.
     *
     * @param registry the CorsRegistry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowCredentials(true).allowedOrigins("*").allowedMethods("POST", "GET");
    }


    /**
     * 設定JSON資料在進入時的物件轉換Mapping規則.
     *
     * @return convert bean
     */
    @Bean(name = "MappingJackson2HttpMessageConverter")
    public MappingJackson2HttpMessageConverter converter() {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().serializationInclusion(JsonInclude.Include.NON_NULL)
                .visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .visibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
                .visibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE).build();

        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }

}

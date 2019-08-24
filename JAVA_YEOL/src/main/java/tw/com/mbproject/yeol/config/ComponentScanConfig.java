package tw.com.mbproject.yeol.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan({
    "tw.com.mbproject.yeol.controller,"
    + "tw.com.mbproject.yeol.service.impl,"
    + "tw.com.mbproject.yeol.repo"})
@Configuration
public class ComponentScanConfig {

}

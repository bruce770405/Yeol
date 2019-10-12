package tw.com.mbproject.yeol.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import tw.com.mbproject.yeol.controller.MessageController;
import tw.com.mbproject.yeol.repo.MessageRepo;
import tw.com.mbproject.yeol.service.impl.MessageServiceImpl;

@ComponentScan(basePackageClasses = {
        MessageController.class, // controller
        MessageRepo.class, // dao
        MessageServiceImpl.class // service
})
@Configuration
public class ComponentScanConfig {

}

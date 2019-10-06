package tw.com.mbproject.yeol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"tw.com.mbproject.yeol.config"})
public class YeolApplication {

	public static void main(String[] args) {
		SpringApplication.run(YeolApplication.class, args);
	}

}

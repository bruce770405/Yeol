package tw.com.mbproject.yeol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 程式進入點.
 * @author BruceHsu
 * @version
 * @serial
 * @see
 */
@SpringBootApplication
@ComponentScan({"tw.com.mbproject.yeol.config"})
public class YeolApplication {

	/**
	 * main.
	 * 
	 * @param args the String[] args.
	 */
	public static void main(String[] args) {
		SpringApplication.run(YeolApplication.class, args);
	}

}

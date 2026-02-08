package eddie.payment.authsdk;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class SecurityConfig {
	@Bean
	public String helloBean() {
		System.out.println("helloBean created");
		return "hello";
	}
}

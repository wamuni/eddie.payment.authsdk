package eddie.payment.authsdk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthSdkSecurityConfig {

	@Bean
	SecurityFilterChain authSdkFilterChain(
		HttpSecurity http,
		AuthSdkProperties props
		) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/actuator/health").permitAll()
					.anyRequest().authenticated()
				)
			.oauth2ResourceServer(oauth2 -> oauth2
					.jwt(jwt -> jwt.jwkSetUri(props.jwksUri())));
		return http.build();
	}
}

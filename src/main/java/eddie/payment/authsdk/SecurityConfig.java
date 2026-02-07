package eddie.payment.authsdk;

@Configuration
public class SecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(a -> a.anyRequest().authenticated());
		return http.build();
	}
}

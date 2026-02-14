package eddie.payment.authsdk;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
@EnableConfigurationProperties(AuthSdkProperties.class)
@Import(AuthSdkSecurityConfig.class)
public class AuthSdkAutoConfiguration {}

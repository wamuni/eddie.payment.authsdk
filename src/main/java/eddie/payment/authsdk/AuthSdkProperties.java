package eddie.payment.authsdk;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth")
public record AuthSdkProperties(String jwksUri) {}

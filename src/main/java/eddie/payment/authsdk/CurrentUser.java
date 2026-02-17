package eddie.payment.authsdk;

import java.util.Set;

public record CurrentUser(
        String userId,      //from JWT "sub"
        String email,       //from JWT "email" (if present)
        Set<String> roles   //from JWT "roles" (if present)
) { }

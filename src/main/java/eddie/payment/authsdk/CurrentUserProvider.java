package eddie.payment.authsdk;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class CurrentUserProvider {

    public CurrentUser currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new UnauthenticatedException("No authenticated user in SecurityContext");
        }

        Object principal = auth.getPrincipal();

        Jwt jwt = extractJwt(principal);
        if (jwt == null) { throw new UnauthenticatedException("Authentication principal is not a JWT: " + principal); }

        String userId = jwt.getSubject();
        String email = jwt.getClaimAsString("email");

        Set<String> roles = new LinkedHashSet<>();
        Object rolesClaim = jwt.getClaims().get("roles");
        if (rolesClaim instanceof Collection<?> c) {
            for (Object r: c) {
                if (r != null) roles.add(r.toString());
            }
        } else if (rolesClaim instanceof String s && !s.isBlank()) {
            roles.add(s);
        }

        return new CurrentUser(userId, email, roles);
    }

    private Jwt extractJwt(Object principal) {
        if (principal instanceof Jwt jwt) return jwt;
        return null;
    }

    public static class UnauthenticatedException extends RuntimeException {
        public UnauthenticatedException(String message) {
            super(message);
        }
    }

}

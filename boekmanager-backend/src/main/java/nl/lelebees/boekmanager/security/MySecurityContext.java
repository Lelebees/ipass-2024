package nl.lelebees.boekmanager.security;

import nl.lelebees.boekmanager.security.domain.Account;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class MySecurityContext implements SecurityContext {
    private final Account user;
    private final String scheme;

    public MySecurityContext(Account user, String scheme) {
        this.user = user;
        this.scheme = scheme;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.user;
    }

    @Override
    public boolean isUserInRole(String s) {
        if (user.getRole() != null) {
            System.out.printf("%s equals %s", s, user.getRole());
            return s.equals(user.getRole());
        }
        return false;
    }

    @Override
    public boolean isSecure() {
        return "https".equals(this.scheme);
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}

















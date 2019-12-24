package huacloud.jwdsj.common.security;

import princess.common.security.Principal;

public class SecurityContextHolder {

    private static final ThreadLocal<Principal> PRINCIPAL = new ThreadLocal<>();

    protected static void setPrincipal(Principal principal) {
        if (principal == null) {
            remove();
        } else {
            PRINCIPAL.set(principal);
        }
    }

    public static Principal getPrincipal() {
        return PRINCIPAL.get();
    }

    protected static void remove() {
        PRINCIPAL.remove();
    }
}


package huacloud.jwdsj.common.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import lombok.extern.slf4j.Slf4j;
import princess.common.constants.BaseConstants.Symbols;
import princess.common.security.Principal;
import princess.common.security.PrincipalGJ;

@Slf4j
public class SecurityFilter implements Filter {

    @Lazy
    @Autowired
    private SecurityImplementor securityImplementor;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader(Symbols.TOKEN);
        if (token == null) {
            token = request.getParameter(Symbols.TOKEN);
        }
        if (token != null) {
            Principal principal = securityImplementor.parseToken(token);
            PrincipalGJ principalGJ = securityImplementor.parseTokenGJ(token);
            SecurityContextHolder.setPrincipal(principal);

            // logger.error("-----------------------------------" + StringUtil.toString(principalGJ));
        }
        try {
            chain.doFilter(request, response);
        } finally {
            SecurityContextHolder.remove();
        }
    }
}

package com.oasisvn.middleware.security;

import com.oasisvn.model.dto.user.UserSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ApiAuthorizationFilter extends BasicAuthenticationFilter {

    ApiAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws IOException, ServletException {

        // Check session
        UserSession userSession = (UserSession) request.getSession().getAttribute("OASIS_SESSION");
        if (userSession == null) {
            chain.doFilter(request, response);
            return;
        }

        // Add Authentication to SecurityContext
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(userSession);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(UserSession userSession) {

        String userId = userSession.getUserId();

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userSession.getRole());
        authorities.add(authority);

        return new UsernamePasswordAuthenticationToken(userId, userSession, authorities);
    }
}

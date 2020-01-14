package com.oasisvn.middleware.security;

import com.oasisvn.middleware.utilities.jwt.JwtUltils;
import io.jsonwebtoken.Claims;
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //Check token
        Claims claims = JwtUltils.verifyToken(request);
        if (null == claims) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(claims);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(Claims claims) {

        String userId = claims.getSubject();

        ArrayList<String> roles = (ArrayList<String>) claims.get("roles");

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        if (null != roles) {
            for (String role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                authorities.add(authority);
            }
        }
        if (null != userId) {
            return new UsernamePasswordAuthenticationToken(userId, null, authorities);
        } else {
            return null;
        }
    }
}

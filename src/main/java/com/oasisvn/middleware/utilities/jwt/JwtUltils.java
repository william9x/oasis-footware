package com.oasisvn.middleware.utilities.jwt;

import com.oasisvn.entity.user.UserEntity;
import com.oasisvn.middleware.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtUltils {
    public static Claims verifyToken(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER);
        if (null == token || false == token.startsWith(SecurityConstants.PREFIX)) return null;

        return Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token.replace(SecurityConstants.PREFIX, ""))
                .getBody();
    }

    public static String generateToken(UserEntity userEntity) {

        Claims claims = Jwts.claims().setSubject(userEntity.getUserId());

        List<String> roles = new ArrayList<>();
        roles.add(userEntity.getRole());

        claims.put("roles", roles);
        claims.put("userid", userEntity.getUserId());

        Date now = new Date();
        Date expireDate = new Date(now.getTime() + SecurityConstants.EXPIRATION);

        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();

        return SecurityConstants.PREFIX + token;
    }
}

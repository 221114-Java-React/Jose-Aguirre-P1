package com.revature.p1.services;

import com.revature.p1.dtos.responses.Principal;
import com.revature.p1.models.Role;
import com.revature.p1.utils.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import java.util.Date;

public class TokenService {
    private JwtConfig jwtConfig;


    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public TokenService() {
        super();

    }

    public String generateToken(Principal subject) {
        long now = System.currentTimeMillis();
        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(subject.getId())
                .setIssuer("p1")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                .setSubject(subject.getUserName())
                .claim("role", subject.getRole())
                .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return tokenBuilder.compact();
    }

    public Principal extractRequesterDetails(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();
            return new Principal(claims.getId(), claims.getSubject(), Role.valueOf(claims.get("role", String.class)));
        } catch (Exception e) {
            return null;
        }
    }
}
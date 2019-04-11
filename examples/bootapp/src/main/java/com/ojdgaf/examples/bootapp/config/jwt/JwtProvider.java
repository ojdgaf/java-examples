package com.ojdgaf.examples.bootapp.config.jwt;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

@Component
public class JwtProvider {
    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expirationInMs}")
    private long expirationInMs;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(Map<String, Object> claims) {
        if (!validateClaims(claims))
            throw new JwtException("Could not create JWT token: invalid claims");

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + expirationInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expiresAt)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    String extractToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");

        if (validateHeader(header))
            return header.substring(7);

        return null;
    }

    String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    String[] extractAuthorityNames(String token) {
        String[] authorityNames = new String[0];

        try {
            Object element = extractClaims(token).get("authorities");
            Collection<String> collection = (Collection<String>) element;
            return collection.toArray(authorityNames);
        } catch (Exception e) {
            return authorityNames;
        }
    }

    void validateToken(String token) {
        extractClaims(token);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private boolean validateHeader(String header) {
        return header != null && header.startsWith("Bearer ");
    }

    private boolean validateClaims(Map<String, Object> claims) {
        if (!claims.containsKey("sub"))
            return false;

        Object subject = claims.get("sub");

        return subject instanceof String && !((String) subject).isEmpty();
    }
}

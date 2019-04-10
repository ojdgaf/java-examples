package com.ojdgaf.examples.bootapp.config.jwt;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import com.ojdgaf.examples.bootapp.services.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Component
public class JwtProvider {
    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expirationInMs}")
    private long expirationInMs;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String username, String[] roleNames) {
        Claims claims = Jwts.claims();
        claims.setSubject(username);
        claims.put("auth", AuthorityUtils.createAuthorityList(roleNames));

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + expirationInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expiresAt)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(extractUsername(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    String extractToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");

        if (validateHeader(header))
            return header.substring(7);

        return null;
    }

    boolean validateToken(String token) {
        extractClaims(token);
        return true;
    }

    private boolean validateHeader(String header) {
        return header != null && header.startsWith("Bearer ");
    }

    private String extractUsername(String token) {
        return extractClaims(token).getBody().getSubject();
    }

    private Jws<Claims> extractClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
    }
}

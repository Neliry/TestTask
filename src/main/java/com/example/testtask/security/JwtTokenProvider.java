package com.example.testtask.security;

import com.example.testtask.exeption.JwtAuthenticationException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final String BEARER_TOKEN_PREFIX = "Bearer ";
    private static final String INVALID_TOKEN_ERROR = "Invalid token";
    @Value("${security.jwt.token.secret}")
    private String secret;
    @Value("${security.jwt.token.expiration}")
    private long expirationInMillis;
    private Key signingKey;

    @PostConstruct
    private void initKey() {
        signingKey = new SecretKeySpec(DatatypeConverter.parseBase64Binary(secret), SignatureAlgorithm.HS512.getJcaName());
    }

    public String createToken(String subject) {
        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + expirationInMillis);
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(signingKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith(BEARER_TOKEN_PREFIX)) {
            return bearerToken.substring(BEARER_TOKEN_PREFIX.length());
        }
        return null;
    }

    public String getSubject(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception exception) {
            throw new JwtAuthenticationException(INVALID_TOKEN_ERROR);
        }
    }
}

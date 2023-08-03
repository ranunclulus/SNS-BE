package com.example.MutsaSNS.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;
import java.time.Instant;

@Slf4j
@Component
public class JwtTokenUtils {
    private final Key signingKey;
    private final JwtParser jwtParser;

    public JwtTokenUtils(@Value("${jwt.secret}") String jwtSecret) {
        this.signingKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        this.jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(this.signingKey)
                .build();
    }

    // JWT 검증 메소드
    public boolean validate(String token) {
        log.info("검증하고자 하는 토큰: ", token);
        try {
            jwtParser.parseClaimsJwt(token).getBody();
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.warn("malformed jwt");
        } catch (ExpiredJwtException e) {
            log.warn("expired jwt presented");
        } catch (UnsupportedJwtException e) {
            log.warn("unsupported jwt");
        } catch (IllegalArgumentException e) {
            log.warn("illegal argument");
        }
        return false;
    }

    // JWT 생성 메소드
    public String generateToken(String username) {
        Claims jwtClaims = Jwts.claims()
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(1000)));
        return Jwts.builder()
                .setClaims(jwtClaims)
                .signWith(signingKey)
                .compact();
    }

    // JWT 해석 메소드
    public Claims parseClaims(String token) {
        return jwtParser
                .parseClaimsJwt(token)
                .getBody();
    }
}

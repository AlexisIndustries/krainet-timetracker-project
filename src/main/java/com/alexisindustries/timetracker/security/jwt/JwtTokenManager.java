package com.alexisindustries.timetracker.security.jwt;

import com.alexisindustries.timetracker.model.Role;
import com.alexisindustries.timetracker.model.User;
import com.alexisindustries.timetracker.service.UserService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenManager {
    @Value("spring.jwt.token.secret")
    private String jwtSecret;
    private UserService userService;
    private SecretKey secretKey;

    @PostConstruct
    private void init() {
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
        secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String createToken(String username, String password, Set<Role> roles) {
        ClaimsBuilder claimsBuilder = Jwts.claims()
                .subject(username)
                .add("roles", roles);

        Claims claims = claimsBuilder.build();

        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * 60 * 60 * 24);

        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        User user = new User();

        user.setUsername(getUsernameFromJwt(token));
        user.setRoles(getRolesFromJwt(token));

        return new UsernamePasswordAuthenticationToken(user, "[protected]", user.getAuthorities());
    }

    @SuppressWarnings("unchecked")
    public Set<Role> getRolesFromJwt(String token) {
        Claims claims = Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        List<String> roles = claims.get("roles", List.class);
        return roles.stream().map(Role::valueOf).collect(Collectors.toSet());
    }

    public String getUsernameFromJwt(String token) {
        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

package lk.ijse.food_ordering_backend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// Creates and validates JWT tokens.
@Component
public class JWTUtil {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration}")
    private Long expiration;

    // Generate token for user
    public String generateToken(String email, String role) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expiration);
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    // Get email from token
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    // Get role from token
    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    // Check if token is valid
    public boolean isTokenValid(String token, String email) {
        try {
            Claims claims = extractClaims(token);
            return claims.getSubject().equals(email) && !isTokenExpired(claims);
        } catch (Exception e) {
            return false;
        }
    }

    // Check if token is expired
    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    // Extract all claims from token
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

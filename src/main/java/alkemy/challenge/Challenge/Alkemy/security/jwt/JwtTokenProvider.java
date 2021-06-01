package alkemy.challenge.Challenge.Alkemy.security.jwt;

import alkemy.challenge.Challenge.Alkemy.model.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Log
@Component
public class JwtTokenProvider {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    @Value("${jwt.secret:9z$C&F)J@NcRfUjXn2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4}")
    private String jwtSecret;

    @Value("${jwt.token-expiration:86400}")
    private int jwtDurationTokenInSeconds;

    public String generateToken(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        //duracion del token
        Date tokenExpirationDate = new Date(System.currentTimeMillis() + (jwtDurationTokenInSeconds * 1000));
        //firma digital
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", TOKEN_TYPE)
                .setSubject(Long.toString(user.getId()))
                .setIssuedAt(new Date())
                .setExpiration(tokenExpirationDate)
                .claim("username", user.getUsername())
                .compact();


    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {

        try {
            Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.info("JWT token signing failed: " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.info("Malformed token: " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.info("The token has expired: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.info("JWT token not supported: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.info("JWT claims empty");
        }
        return false;


    }

}

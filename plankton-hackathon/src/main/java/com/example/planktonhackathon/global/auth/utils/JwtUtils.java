package com.example.planktonhackathon.global.auth.utils;

import com.example.planktonhackathon.global.auth.exception.AuthErrorCode;
import com.example.planktonhackathon.global.exception.RestApiException;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

/**
 * JWT 유틸 클래스
 */
@Component
public class JwtUtils {
  private final SecretKey secretKey;

  public JwtUtils(@Value("${spring.jwt.secret}") String secret) {
    secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
  }

  public String createJwt(String category, String email, String role, Long expiredMs) {
    return Jwts.builder()
               .claim("category", category)
               .claim("email", email)
               .claim("role", role)
               .issuedAt(new Date(System.currentTimeMillis()))
               .expiration(new Date(System.currentTimeMillis() + expiredMs))
               .signWith(secretKey)
               .compact();
  }

  public String getCategory(String token) {
    String category;
    try {
      category = Jwts.parser()
                     .verifyWith(secretKey)
                     .build()
                     .parseSignedClaims(token)
                     .getPayload()
                     .get("category", String.class);
    } catch (Exception e) {
      throw new RestApiException(AuthErrorCode.JWT_PARSING_ERROR);
    }
    return category;
  }

  public String getEmail(String token) {
    String email;
    try {
      email = Jwts.parser()
                        .verifyWith(secretKey)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload()
                        .get("email", String.class);
    } catch (Exception e) {
      throw new RestApiException(AuthErrorCode.JWT_PARSING_ERROR);
    }
    return email;
  }

  public String getRole(String token) {
    String role;
    try {
      role =
          Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    } catch (Exception e) {
      throw new RestApiException(AuthErrorCode.JWT_PARSING_ERROR);
    }
    return role;
  }

  @PostConstruct
  public void init() {
    // JWT 초기화 로직
    Jwts.parser().build();
  }

  public Boolean isExpired(String token) {
    boolean before;
    try {
      before = Jwts.parser()
                   .verifyWith(secretKey)
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .getExpiration()
                   .before(new Date());
    } catch (Exception e) {
      throw new RestApiException(AuthErrorCode.JWT_EXPIRED);
    }
    return before;
  }
}

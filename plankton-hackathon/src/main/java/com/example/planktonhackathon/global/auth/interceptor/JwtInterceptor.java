package com.example.planktonhackathon.global.auth.interceptor;

import com.example.planktonhackathon.global.auth.annotation.Admin;
import com.example.planktonhackathon.global.auth.annotation.Auth;
import com.example.planktonhackathon.global.auth.context.AuthContext;
import com.example.planktonhackathon.global.auth.context.AuthContextHolder;
import com.example.planktonhackathon.global.auth.exception.AuthErrorCode;
import com.example.planktonhackathon.global.auth.utils.JwtUtils;
import com.example.planktonhackathon.global.exception.RestApiException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;


/**
 * JWT 인증 인터셉터
 */
@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {
  private final JwtUtils jwtUtils;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
      return true;
    }

    String authorization = request.getHeader("Authorization");
    boolean isAuthRequired = checkAnnotation(handler, Auth.class);
    boolean isAdminRequired = checkAnnotation(handler, Admin.class);
    if (!isAuthRequired && !isAdminRequired) {
      if (authorization == null) {
        return true;
      }
      saveContext(authorization);
      return true;
    }

    boolean isAuthenticated = authorizeAndSaveContext(authorization);
    if (isAdminRequired) {
      if (!isAdminRole(AuthContextHolder.getAuthContext().getRole())) {
        throw new RestApiException(AuthErrorCode.UNAUTHORIZED);
      }
    }

    return isAuthenticated;
  }

  /**
   * 인증하고 컨텍스트 저장하는 메서드
   *
   * @param authorization
   * @return 인증 성공 여부
   */
  private boolean authorizeAndSaveContext(String authorization) {
    String token;
    if (authorization == null || !authorization.startsWith("Bearer ")) {
      throw new RestApiException(AuthErrorCode.JWT_PARSING_ERROR);
    }
    token = authorization.substring(7);
    jwtUtils.isExpired(token);
    AuthContextHolder.setAuthContext(new AuthContext(jwtUtils.getEmail(token), jwtUtils.getRole(token)));
    return true;
  }

  /**
   * Auth & Admin 어노테이션 존재하는지 확인하는 메소드
   *
   * @param handler
   * @param annotationClass
   * @return
   */
  private boolean checkAnnotation(Object handler, Class<? extends Annotation> annotationClass) {
    if (handler instanceof ResourceHttpRequestHandler) {
      return false;
    }

    HandlerMethod handlerMethod = (HandlerMethod) handler;
    return handlerMethod.getMethodAnnotation(annotationClass) != null ||
            handlerMethod.getBeanType().getAnnotation(annotationClass) != null;
  }

  /**
   * 굳이 인증이 필요 없는데 컨텍스트는 저장하는 메소드
   *
   * @param authorization
   */
  private void saveContext(String authorization) {
    if (authorization == null || !authorization.startsWith("Bearer ")) {
      return;
    }
    String token = authorization.substring(7);
    try {
      jwtUtils.isExpired(token);
    } catch (Exception e) {
      return;
    }
    AuthContextHolder.setAuthContext(new AuthContext(jwtUtils.getEmail(token), jwtUtils.getRole(token)));
  }

  /**
   * 어드민 권한 검사를 수행하는 메서드
   *
   * @param role
   * @return 어드민 권한 여부
   */
  private boolean isAdminRole(String role) {
    return "ADMIN".equals(role); // 권한 검사의 실제 구현을 여기에 작성합니다.
  }
}

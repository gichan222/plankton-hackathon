package com.example.planktonhackathon.global.auth.service;

import com.example.planktonhackathon.global.auth.context.AuthContext;
import com.example.planktonhackathon.global.auth.context.AuthContextHolder;
import com.example.planktonhackathon.global.auth.exception.AuthErrorCode;
import com.example.planktonhackathon.global.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * 인증된 사용자 정보 가져오기
 */
@Service
@RequiredArgsConstructor
public class AuthMemberService {
  /**
   * 인증 컨텍스트 반환
   *
   * @return 인증 컨텍스트
   */
  public AuthContext getAuthContext() {
    return AuthContextHolder.getAuthContext();
  }

  /**
   * 인증된 멤버의 이메일을 세션에서 반환
   *
   * @return 이메일 String
   */
  public String getMemberEmail() {
    AuthContext authContext = getAuthContext();
    if (authContext == null) {
      throw new RestApiException(AuthErrorCode.NO_USER_INFO);
    }
    return authContext.getEmail();
  }

}


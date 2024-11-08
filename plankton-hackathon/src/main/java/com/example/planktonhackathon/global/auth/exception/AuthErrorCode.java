package com.example.planktonhackathon.global.auth.exception;

import com.example.planktonhackathon.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


/**
 *
 */
@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {

  NO_USER_INFO(HttpStatus.FORBIDDEN, "로그인이 되지 않았습니다"),
  JWT_PARSING_ERROR(HttpStatus.FORBIDDEN, "JWT 토큰을 파싱할 수 없습니다."),
  JWT_EXPIRED(HttpStatus.FORBIDDEN, "JWT 토큰이 만료되었습니다"),
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "일반 사용자는 접근할 수 없습니다."),
  ALREADY_EXIST_USER_INFO(HttpStatus.BAD_REQUEST, "이미 존재하는 사용자입니다.")
  ;

  private final HttpStatus httpStatus;
  private final String message;
}

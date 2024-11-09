package com.example.planktonhackathon.domain.member.exception;

import com.example.planktonhackathon.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {

    NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "일치하지 않는 비밀번호입니다."),
    TEAM_EXIST(HttpStatus.BAD_REQUEST, "이미 팀이 존재합니다.");

    private final HttpStatus httpStatus;
    private final String message;
}

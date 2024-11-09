package com.example.planktonhackathon.domain.mission.exception;

import com.example.planktonhackathon.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements ErrorCode{

    HAVE_TO_SOLVE_LAST_QUIZ(HttpStatus.BAD_REQUEST, "전 날의 퀴즈를 먼저 해결해야 합니다.");

    private final HttpStatus httpStatus;
    private final String message;
}

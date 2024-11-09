package com.example.planktonhackathon.domain.attraction.exception;

import com.example.planktonhackathon.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AttractionErrorCode implements ErrorCode{

    ATTRACTION_DOES_NOT_EXIST(HttpStatus.BAD_REQUEST, "챌린지 정보가 존재하지 않습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
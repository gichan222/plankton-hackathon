package com.example.planktonhackathon.domain.gathering.exception;

import com.example.planktonhackathon.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GatheringErrorCode implements ErrorCode {

    ALREADY_WRITE_TODAY(HttpStatus.BAD_REQUEST, "오늘 글을 작성한 이력이 있습니다."),
    CAN_NOT_GET_GATHERING(HttpStatus.BAD_REQUEST, "참여한 이력이 없어 조회가 불가능합니다."),
    FULL_GATHERING(HttpStatus.BAD_REQUEST,"이미 구인이 완료된 글입니다."),
    CAN_NOT_SELF(HttpStatus.BAD_REQUEST, "자기 글에는 좋아요를 누를 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}

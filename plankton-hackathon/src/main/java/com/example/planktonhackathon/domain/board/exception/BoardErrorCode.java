package com.example.planktonhackathon.domain.board.exception;

import com.example.planktonhackathon.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BoardErrorCode implements ErrorCode {
    BOARD_NOT_FOUND(HttpStatus.BAD_REQUEST, "게시글이 존재하지 않습니다."),
    CAN_NOT_WRITE(HttpStatus.BAD_REQUEST,"게시글은 하루 한 번만 작성할 수 있습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}




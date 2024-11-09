package com.example.planktonhackathon.domain.board.controller;

import com.example.planktonhackathon.domain.board.request.BoardCommentWriteRequest;
import com.example.planktonhackathon.domain.board.request.BoardWriteRequest;
import com.example.planktonhackathon.domain.board.response.BoardGetResponse;
import com.example.planktonhackathon.domain.board.service.BoardService;
import com.example.planktonhackathon.global.auth.annotation.Auth;
import com.example.planktonhackathon.global.common.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "게시판 API", description = "게시판 글 작성에 대한 처리를 담당하는 API입니다")
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @Auth
    @Operation(summary = "게시글 작성")
    @PostMapping()
    public ResponseEntity<SuccessResponse> writeBoard(@ModelAttribute BoardWriteRequest boardWriteRequest)
            throws IOException {
        boardService.writeBoard(boardWriteRequest);
        return ResponseEntity.ok(new SuccessResponse());
    }

    @Auth
    @Operation(summary = "댓글 작성")
    @PostMapping("/comment")
    public ResponseEntity<SuccessResponse> writeBoardComment(@RequestBody BoardCommentWriteRequest boardCommentWriteRequest) {
        boardService.writeComment(boardCommentWriteRequest);
        return ResponseEntity.ok(new SuccessResponse());
    }

    @Auth
    @Operation(summary = "게시글 조회")
    @GetMapping
    public ResponseEntity<List<BoardGetResponse>> getBoard(){
        return ResponseEntity.ok().body(boardService.getBoard());
    }
}

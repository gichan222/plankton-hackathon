package com.example.planktonhackathon.domain.member.controller;

import com.example.planktonhackathon.domain.member.request.MemberQuizRequest;
import com.example.planktonhackathon.domain.member.response.MemberGetQuizResponse;
import com.example.planktonhackathon.domain.member.service.MemberQuizService;
import com.example.planktonhackathon.global.auth.annotation.Auth;
import com.example.planktonhackathon.global.common.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "미션 맞추기 API", description = "미션 맞추기에 대한 처리를 담당하는 API입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class MemberQuizController {

    private final MemberQuizService memberQuizService;

    // 퀴즈 답변 제출 메서드
    @Auth
    @Operation(summary = "퀴즈 답변 제출 API")
    @PostMapping
    public ResponseEntity<SuccessResponse> submitQuizAnswer(@RequestBody MemberQuizRequest memberQuizRequest) {
        boolean isCorrect = memberQuizService.quizAnswer(memberQuizRequest);
        return ResponseEntity.ok(new SuccessResponse());
    }

    // 현재 퀴즈 조회 메서드
    @Auth
    @Operation(summary = "퀴즈 문제 조회 API")
    @GetMapping
    public ResponseEntity<MemberGetQuizResponse> getQuiz() {
        MemberGetQuizResponse quizResponse = memberQuizService.getQuiz();
        return ResponseEntity.ok().body(quizResponse);
    }
}

package com.example.planktonhackathon.domain.member.controller;

import com.example.planktonhackathon.domain.member.request.MemberEmailExistRequest;
import com.example.planktonhackathon.domain.member.request.MemberJoinRequest;
import com.example.planktonhackathon.domain.member.request.MemberLoginRequest;
import com.example.planktonhackathon.domain.member.request.MemberNickNameExistRequest;
import com.example.planktonhackathon.domain.member.response.MemberBadgeResponse;
import com.example.planktonhackathon.domain.member.response.MemberInfoResponse;
import com.example.planktonhackathon.domain.member.response.MemberLoginInfoResponse;
import com.example.planktonhackathon.domain.member.service.MemberBadgeService;
import com.example.planktonhackathon.domain.member.service.MemberService;
import com.example.planktonhackathon.global.auth.annotation.Auth;
import com.example.planktonhackathon.global.common.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 관리 API", description = "회원에 대한 처리를 담당하는 API입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {
    public final MemberService memberService;
    public final MemberBadgeService memberBadgeService;

    @Operation(summary = "회원 가입")
    @PostMapping("/sign-up")
    public ResponseEntity<SuccessResponse> joinMember(@Valid @RequestBody MemberJoinRequest memberJoinRequest){
        memberService.join(memberJoinRequest);
        return ResponseEntity.ok(new SuccessResponse());
    }

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<MemberLoginInfoResponse> loginMember(@Valid @RequestBody MemberLoginRequest memberLoginRequest){
        MemberLoginInfoResponse memberLoginInfoResponse = memberService.login(memberLoginRequest);
        return ResponseEntity.ok().body(memberLoginInfoResponse);
    }

    @Auth
    @Operation(summary = "멤버 정보 조회")
    @GetMapping("profile")
    public ResponseEntity<MemberInfoResponse> getMemberInfo(){
        MemberInfoResponse memberInfoResponse = memberService.getMemberInfo();
        return ResponseEntity.ok().body(memberInfoResponse);
    }

    @Operation(summary = "이메일 중복 체크")
    @PostMapping("check-email-duplicate")
    public ResponseEntity<SuccessResponse> isMemberEmailExist(@Valid @RequestBody MemberEmailExistRequest memberEmailExistRequest){
        memberService.isMemberEmailExist(memberEmailExistRequest);
        return ResponseEntity.ok(new SuccessResponse());
    }

    @Operation(summary = "닉네임 중복 체크")
    @PostMapping("check-nickname-duplicate")
    public ResponseEntity<SuccessResponse> isMemberNickNameExist(@Valid @RequestBody MemberNickNameExistRequest memberNickNameExistRequest){
        memberService.isMemberNickNameExist(memberNickNameExistRequest);
        return ResponseEntity.ok(new SuccessResponse());
    }

    @Auth
    @Operation(summary = "멤버 뱃지 조회")
    @GetMapping("/badge")
    public ResponseEntity<List<MemberBadgeResponse>> getBadge(){
        return ResponseEntity.ok().body(memberBadgeService.getMemberBadgeResponses());
    }
}

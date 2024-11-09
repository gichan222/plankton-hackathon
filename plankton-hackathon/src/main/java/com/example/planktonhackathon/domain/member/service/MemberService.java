package com.example.planktonhackathon.domain.member.service;

import com.example.planktonhackathon.domain.member.domain.Member;
import com.example.planktonhackathon.domain.member.exception.MemberErrorCode;
import com.example.planktonhackathon.domain.member.repository.MemberRepository;
import com.example.planktonhackathon.domain.member.request.MemberEmailExistRequest;
import com.example.planktonhackathon.domain.member.request.MemberJoinRequest;
import com.example.planktonhackathon.domain.member.request.MemberLoginRequest;
import com.example.planktonhackathon.domain.member.request.MemberNickNameExistRequest;
import com.example.planktonhackathon.domain.member.response.MemberInfoResponse;
import com.example.planktonhackathon.domain.member.response.MemberLoginInfoResponse;
import com.example.planktonhackathon.global.auth.exception.AuthErrorCode;
import com.example.planktonhackathon.global.auth.service.AuthMemberService;
import com.example.planktonhackathon.global.auth.utils.JwtUtils;
import com.example.planktonhackathon.global.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtils jwtUtils;
    private final AuthMemberService authMemberService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public MemberLoginInfoResponse login(MemberLoginRequest memberLoginRequest){
        Member member = memberRepository.findByEmail(memberLoginRequest.getEmail())
                .orElseThrow(() -> new RestApiException(AuthErrorCode.NO_USER_INFO));

        // 입력된 비밀번호를 암호화하여 비교
        boolean isEqualPassword = passwordEncoder.matches(memberLoginRequest.getPassword(), member.getPassword());
        if(!isEqualPassword){
            throw new RestApiException(MemberErrorCode.NOT_MATCH_PASSWORD);
        }

        String accessToken =
                jwtUtils.createJwt("access_token", member.getEmail(), member.getRole(), 2 * 24 * 60 * 60 * 1000L);
        return MemberLoginInfoResponse.of(member, accessToken);
    }

    @Transactional
    public MemberInfoResponse getMemberInfo(){
        Member member = memberRepository.findByEmail(authMemberService.getMemberEmail())
                .orElseThrow(() -> new RestApiException(AuthErrorCode.NO_USER_INFO));
        return MemberInfoResponse.of(member);
    }

    @Transactional
    public void join(MemberJoinRequest memberJoinRequest) {
        isMemberEmailExist(new MemberEmailExistRequest(memberJoinRequest.getEmail()));
        isMemberNickNameExist(new MemberNickNameExistRequest(memberJoinRequest.getNickName()));

        // 비밀번호 암호화 후 저장
        String encodedPassword = passwordEncoder.encode(memberJoinRequest.getPassword());
        memberRepository.save(new Member(memberJoinRequest.getEmail(), encodedPassword, memberJoinRequest.getNickName()));
    }

    @Transactional
    public void isMemberEmailExist(MemberEmailExistRequest memberEmailExistRequest){
        memberRepository.findByEmail(memberEmailExistRequest.getEmail())
                .ifPresent(member -> {
                    throw new RestApiException(AuthErrorCode.ALREADY_EXIST_USER_INFO);
                });
    }

    @Transactional
    public void isMemberNickNameExist(MemberNickNameExistRequest memberNickNameExistRequest){
        memberRepository.findByNickName(memberNickNameExistRequest.getNickName())
                .ifPresent(member -> {
                    throw new RestApiException(AuthErrorCode.ALREADY_EXIST_USER_INFO);
                });
    }
}

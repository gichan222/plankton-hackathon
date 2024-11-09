package com.example.planktonhackathon.domain.member.service;

import com.example.planktonhackathon.domain.member.domain.Member;
import com.example.planktonhackathon.domain.member.domain.MemberQuiz;
import com.example.planktonhackathon.domain.member.exception.MemberErrorCode;
import com.example.planktonhackathon.domain.member.repository.MemberQuizRepository;
import com.example.planktonhackathon.domain.member.repository.MemberRepository;
import com.example.planktonhackathon.domain.member.request.MemberQuizRequest;
import com.example.planktonhackathon.domain.member.response.MemberGetQuizResponse;
import com.example.planktonhackathon.domain.mission.domain.MissionEntity;
import com.example.planktonhackathon.domain.mission.repository.MissionRepository;
import com.example.planktonhackathon.global.auth.exception.AuthErrorCode;
import com.example.planktonhackathon.global.auth.service.AuthMemberService;
import com.example.planktonhackathon.global.exception.RestApiException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberQuizService {

    private final MemberRepository memberRepository;
    private final AuthMemberService authMemberService;
    private final MemberQuizRepository memberQuizRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public boolean quizAnswer(MemberQuizRequest memberQuizRequest){
        Member member = memberRepository.findByEmail(authMemberService.getMemberEmail())
                .orElseThrow(() -> new RestApiException(AuthErrorCode.NO_USER_INFO));
        MemberQuiz memberQuiz = memberQuizRepository.findByEmailAndLocalDate(member.getEmail(), LocalDate.now().minusDays(1))
                .orElseThrow(() -> new RestApiException(MemberErrorCode.TEAM_DOES_NOT_MATCH));
        System.out.println(1);
        Long missionId;
        if(memberQuiz.getMissionId()%2 ==0){
            missionId = memberQuiz.getMissionId() -1;
        }else{
            missionId = memberQuiz.getMissionId() + 1;
        }
        MissionEntity missionEntity = missionRepository.findById(missionId)
                .orElseThrow(() -> new RestApiException(MemberErrorCode.TEAM_DOES_NOT_MATCH));
        System.out.println(2);
        if(missionEntity.getMissionText().contains(memberQuizRequest.getAnswer())){
            memberQuizRepository.deleteByEmailAndLocalDate(member.getEmail(), LocalDate.now().minusDays(1));
            return true;
        }
        return false;
    }

    @Transactional
    public MemberGetQuizResponse getQuiz(){
        Member member = memberRepository.findByEmail(authMemberService.getMemberEmail())
                .orElseThrow(() -> new RestApiException(AuthErrorCode.NO_USER_INFO));
        System.out.println(member.getEmail());
        System.out.println(LocalDate.now().minusDays(1));
        System.out.println(LocalDateTime.now());
        MemberQuiz memberQuiz = memberQuizRepository.findByEmailAndLocalDate(member.getEmail(), LocalDate.now().minusDays(1))
                .orElseThrow(() -> new RestApiException(MemberErrorCode.TEAM_DOES_NOT_MATCH));
        System.out.println(1);
        Long missionId;
        if(memberQuiz.getMissionId()%2 ==0){
            missionId = memberQuiz.getMissionId() -1;
        }else{
            missionId = memberQuiz.getMissionId() + 1;
        }
        MissionEntity missionEntity = missionRepository.findById(missionId)
                .orElseThrow(() -> new RestApiException(MemberErrorCode.TEAM_DOES_NOT_MATCH));
        System.out.println(2);
        String quiz = missionEntity.getMissionText();
        quiz = quiz.replaceAll("(?<=Take a photo of).*", " ***");
        return new MemberGetQuizResponse(quiz);
    }
}

package com.example.planktonhackathon.domain.mission.service;

import com.example.planktonhackathon.domain.member.domain.Member;
import com.example.planktonhackathon.domain.member.repository.MemberQuizRepository;
import com.example.planktonhackathon.domain.member.repository.MemberRepository;
import com.example.planktonhackathon.domain.mission.domain.MissionEntity;
import com.example.planktonhackathon.domain.mission.exception.MissionErrorCode;
import com.example.planktonhackathon.domain.mission.repository.MissionRepository;
import com.example.planktonhackathon.domain.mission.response.MissionResponse;
import com.example.planktonhackathon.global.auth.exception.AuthErrorCode;
import com.example.planktonhackathon.global.auth.service.AuthMemberService;
import com.example.planktonhackathon.global.exception.RestApiException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberQuizRepository memberQuizRepository;
    private final AuthMemberService authMemberService;
    private final MemberRepository memberRepository;

    @Transactional
    public MissionResponse getMissionInfo(String district, String bigCategory){
        Random random = new Random();

        // 0 또는 1을 랜덤으로 뽑기
        int value = random.nextInt(2);
        Member member = memberRepository.findByEmail(authMemberService.getMemberEmail())
                .orElseThrow(() -> new RestApiException(AuthErrorCode.NO_USER_INFO));
        memberQuizRepository.findByEmailAndLocalDate(member.getEmail(), LocalDate.now().minusDays(1))
                .ifPresent(quiz -> {
                    throw new RestApiException(MissionErrorCode.HAVE_TO_SOLVE_LAST_QUIZ);
                });
        if(member.teamExist(member, value)){
            return new MissionResponse(member,missionRepository.findById(member.getChallengeId()).get().getMissionText());
        } // 이미 팀 존재하면 패스
        List<MissionEntity> missionList = missionRepository.findAllByDistrictAndBigCategoryAndLocalDate(district,bigCategory,
                LocalDate.now());
        member.challengeIdDetermine(missionList.get(value).getId());

        return new MissionResponse(member,missionList.get(value).getMissionText());
    }
}

package com.example.planktonhackathon.domain.gathering.service;

import com.example.planktonhackathon.domain.gathering.domain.Gathering;
import com.example.planktonhackathon.domain.gathering.domain.GatheringJoin;
import com.example.planktonhackathon.domain.gathering.exception.GatheringErrorCode;
import com.example.planktonhackathon.domain.gathering.repository.GatheringJoinRepository;
import com.example.planktonhackathon.domain.gathering.repository.GatheringRepository;
import com.example.planktonhackathon.domain.gathering.request.GatheringWriteRequest;
import com.example.planktonhackathon.domain.gathering.response.GatheringGetResponse;
import com.example.planktonhackathon.domain.member.domain.Member;
import com.example.planktonhackathon.domain.member.repository.MemberRepository;
import com.example.planktonhackathon.global.auth.exception.AuthErrorCode;
import com.example.planktonhackathon.global.auth.service.AuthMemberService;
import com.example.planktonhackathon.global.exception.RestApiException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GatheringService {

    private final GatheringRepository gatheringRepository;
    private final MemberRepository memberRepository;
    private final AuthMemberService authMemberService;
    private final GatheringJoinRepository gatheringJoinRepository;

    @Transactional
    public void writeGathering(GatheringWriteRequest gatheringWriteRequest){
        Member member = memberRepository.findByEmail(authMemberService.getMemberEmail())
                .orElseThrow(() -> new RestApiException(AuthErrorCode.NO_USER_INFO));
        if(gatheringRepository.existsByNickNameAndChallengeId(member.getNickName(), member.getChallengeId())){
            throw new RestApiException(GatheringErrorCode.ALREADY_WRITE_TODAY);
        }
        Gathering gathering = new Gathering(member.getNickName(), gatheringWriteRequest.getText(), member.getChallengeId(),
                gatheringWriteRequest.getMaxCount());
        gatheringRepository.save(gathering);
    }

    @Transactional
    public List<GatheringGetResponse> getGathering(Long challengeId){
        Member member = memberRepository.findByEmail(authMemberService.getMemberEmail())
                .orElseThrow(() -> new RestApiException(AuthErrorCode.NO_USER_INFO));

        // 회원의 challengeId와 요청한 challengeId가 같은지 확인
        if (!member.getChallengeId().equals(challengeId)) {
            throw new RestApiException(GatheringErrorCode.CAN_NOT_GET_GATHERING);
        }

        // 챌린지 ID로 모임 목록 조회
        List<Gathering> gatherings = gatheringRepository.findByChallengeId(challengeId);

        // Gathering 리스트를 GatheringGetResponse 리스트로 변환
        return gatherings.stream()
                .map(gathering -> {
                    // 각 Gathering 객체의 check 상태 확인
                    boolean check = gatheringJoinRepository.existsByEmailAndChallengeId(member.getEmail(), gathering.getId());
                    return new GatheringGetResponse(gathering, check);
                })
                .collect(Collectors.toList());
    }


    //join 안 하긴 했는데 최고 개수 넘으면 예외 반환
    @Transactional
    public void joinGathering(Long gatheringId){
        Gathering gathering = gatheringRepository.findById(gatheringId)
                .orElseThrow(() -> new RestApiException(GatheringErrorCode.CAN_NOT_GET_GATHERING));
        Long challengeId = gathering.getChallengeId();
        Member member = memberRepository.findByEmail(authMemberService.getMemberEmail())
                .orElseThrow(() -> new RestApiException(AuthErrorCode.NO_USER_INFO)); // 유저 없으면 예외
        if(gathering.getNickName().equals(member.getNickName())){
            throw new RestApiException(GatheringErrorCode.CAN_NOT_SELF);
        }
        if(!(member.getChallengeId() == challengeId)){
            throw new RestApiException(GatheringErrorCode.CAN_NOT_GET_GATHERING); // 챌린지 id랑 다르면 예외
        }
        if(gatheringJoinRepository.existsByEmailAndChallengeId(member.getEmail(), challengeId)){
            gathering.decreaseLikeCount();
            gatheringRepository.save(gathering);
            gatheringJoinRepository.deleteByEmailAndChallengeId(member.getEmail(), challengeId);
            return;
        }

        gathering.increaseLikeCount();
        gatheringRepository.save(gathering);
        GatheringJoin gatheringJoin = new GatheringJoin(member.getEmail(), challengeId);
        gatheringJoinRepository.save(gatheringJoin);

    }
}

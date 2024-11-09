package com.example.planktonhackathon.domain.gathering.service;

import com.example.planktonhackathon.domain.gathering.domain.Gathering;
import com.example.planktonhackathon.domain.gathering.exception.GatheringErrorCode;
import com.example.planktonhackathon.domain.gathering.repository.GatheringRepository;
import com.example.planktonhackathon.domain.gathering.request.GatheringGetRequest;
import com.example.planktonhackathon.domain.gathering.request.GatheringWriteRequest;
import com.example.planktonhackathon.domain.gathering.response.GatheringGetResponse;
import com.example.planktonhackathon.domain.member.domain.Member;
import com.example.planktonhackathon.domain.member.repository.MemberRepository;
import com.example.planktonhackathon.global.auth.exception.AuthErrorCode;
import com.example.planktonhackathon.global.auth.service.AuthMemberService;
import com.example.planktonhackathon.global.exception.RestApiException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GatheringService {

    private final GatheringRepository gatheringRepository;
    private final MemberRepository memberRepository;
    private final AuthMemberService authMemberService;

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
    public GatheringGetResponse getGathering(Long challengeId){
        Member member = memberRepository.findByEmail(authMemberService.getMemberEmail())
                .orElseThrow(() -> new RestApiException(AuthErrorCode.NO_USER_INFO));
        if(!(member.getChallengeId() == challengeId)){
            throw new RestApiException(GatheringErrorCode.CAN_NOT_GET_GATHERING);
        }
        List<Gathering> gatherings = gatheringRepository.findByChallengeId(challengeId);
        return new GatheringGetResponse(gatherings);
    }
}

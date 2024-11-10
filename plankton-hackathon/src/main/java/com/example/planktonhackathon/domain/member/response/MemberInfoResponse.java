package com.example.planktonhackathon.domain.member.response;

import com.example.planktonhackathon.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberInfoResponse {
    private String email;
    private String nickName;
    private Integer team;
    private Long challengeId;
    private Integer level;
    private Integer challengeCount;
    private Integer miniGameCount;
    private String district;
    private String bigCategory;

    // Member 객체를 받아서 Response 객체로 변환
    public static MemberInfoResponse of(Member member){
        return new MemberInfoResponse(
                member.getEmail(),
                member.getNickName(),
                member.getTeam(),
                member.getChallengeId(),
                member.getLevel(),
                member.getChallengeCount(),
                member.getMiniGameCount(),
                member.getDistrict(),
                member.getBigCategory()
        );
    }
}

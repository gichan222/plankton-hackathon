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
public class MemberLoginInfoResponse {
    private String email;
    private String nickName;
    private Integer team;
    private Long challengeId;
    private Integer level;
    private Integer challengeCount;
    private Integer miniGameCount;
    private String district;
    private String bigCategory;
    private String accessToken;

    // Member 객체와 accessToken을 받아서 MemberLoginInfoResponse를 생성
    public static MemberLoginInfoResponse of(Member member, String accessToken){
        return new MemberLoginInfoResponse(
                member.getEmail(),
                member.getNickName(),
                member.getTeam(),
                member.getChallengeId(),
                member.getLevel(),
                member.getChallengeCount(),
                member.getMiniGameCount(),
                member.getDistrict(),
                member.getBigCategory(),
                accessToken
        );
    }
}

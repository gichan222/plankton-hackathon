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
    private Integer team;
    private Long challengeId;
    private String accessToken;

    public static MemberLoginInfoResponse of(Member member, String accessToken){
        return new MemberLoginInfoResponse(member.getEmail(), member.getTeam(), member.getChallengeId(), accessToken);
    }
}

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
    private Integer team;
    private Long challengeId;

    public static MemberInfoResponse of(Member member){
        return new MemberInfoResponse(member.getEmail(), member.getTeam(), member.getChallengeId());
    }
}

package com.example.planktonhackathon.domain.mission.response;


import com.example.planktonhackathon.domain.member.domain.Member;
import com.example.planktonhackathon.domain.member.response.MemberInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MissionResponse extends MemberInfoResponse {

    private String mission;

    public MissionResponse(Member member, String mission) {
        super(member.getEmail(), member.getTeam(), member.getChallengeId());
        this.mission = mission;
    }
}

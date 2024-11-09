package com.example.planktonhackathon.domain.mission.response;


import com.example.planktonhackathon.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MissionResponse {

    private String mission;
    private String email;
    private Integer team;
    private Long challengeId;

    public MissionResponse(Member member, String mission) {
        this.email = member.getEmail();
        this.team = member.getTeam();
        this.challengeId = member.getChallengeId();
        this.mission = mission;
    }
}

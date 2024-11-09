package com.example.planktonhackathon.domain.member.domain;

import com.example.planktonhackathon.domain.member.exception.MemberErrorCode;
import com.example.planktonhackathon.global.exception.RestApiException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 멤버 엔티티
 */
@Entity
@NoArgsConstructor
@Getter
public class Member {
    @Id
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickName;

    @Column
    private Integer team;

    @Column
    private Long challengeId;

    @Column(nullable = false)
    private String role;

    public Member(String email, String password, String nickName){
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        role = "USER";
    }

    public void teamDetermine(int team){
        this.team = team;
    }

    public void challengeIdDetermine(Long challengeId){
        this.challengeId = challengeId;
    }

    public void teamExist(Member member){
        if(member.getTeam() != null){
            throw new RestApiException(MemberErrorCode.TEAM_EXIST);
        }
    }

}

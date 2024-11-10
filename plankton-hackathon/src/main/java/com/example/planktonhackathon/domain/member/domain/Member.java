package com.example.planktonhackathon.domain.member.domain;

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
    private Integer level;

    @Column(nullable = false)
    private Integer challengeCount;

    @Column(nullable = false)
    private Integer miniGameCount;

    @Column
    private String district;

    @Column
    private String bigCategory;

    @Column(nullable = false)
    private String role;

    public Member(String email, String password, String nickName){
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.level = 1;
        this.challengeCount = 0;
        this.miniGameCount = 0;
        role = "USER";
    }

    public void levelUpCheck(){
        if(challengeCount >= 10 && miniGameCount >= 6){
            this.level = 999;
            return;
        }
        if(challengeCount >= 8 && miniGameCount >= 4){
            this.level = 5;
            return;
        }
        if(challengeCount >= 5 && miniGameCount >= 2){
            this.level = 4;
            return;
        }
        if(challengeCount >= 3 && miniGameCount >= 1){
            this.level = 3;
            return;
        }
        if(challengeCount >= 1 && miniGameCount >= 0){
            this.level = 2;
            return;
        }

    }

    public void increaseChallengeCount(){
        this.challengeCount++;
    }


    public void increaseMiniGameCount(){
        this.miniGameCount++;
    }


    public void teamDetermine(Integer team){
        this.team = team;
    }

    public void challengeIdDetermine(Long challengeId){
        this.challengeId = challengeId;
    }

    public boolean teamExist(Member member, Integer team){
        if(member.getTeam() != null){
            return true;
        }
        teamDetermine(team);
        return false;
    }

    public void districtDetermine(String district) {
        this.district = district;
    }

    public void bigCategoryDetermine(String bigCategory) {
        this.bigCategory = bigCategory;
    }


}

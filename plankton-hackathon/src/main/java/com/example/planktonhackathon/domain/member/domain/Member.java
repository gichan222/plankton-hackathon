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

    @Column(nullable = false)
    private String role;

    public Member(String email, String password, String nickName){
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        role = "USER";
    }

    public boolean isEqualPassword(String password){
        return this.password.equals(password);
    }

}

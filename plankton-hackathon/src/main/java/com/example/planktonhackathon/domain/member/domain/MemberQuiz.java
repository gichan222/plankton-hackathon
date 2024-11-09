package com.example.planktonhackathon.domain.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class MemberQuiz {

    @Id
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate localDate;

    @Column(nullable = false)
    private Long missionId;
}

package com.example.planktonhackathon.domain.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Board {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false, length = 1000)
    private String writing;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String bigCategory;

    @Column(nullable = false)
    private Long challengeId;
}

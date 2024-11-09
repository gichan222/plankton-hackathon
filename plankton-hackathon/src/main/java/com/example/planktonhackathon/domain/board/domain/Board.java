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
    private String title;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false, length = 1000)
    private String writingText;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String bigCategory;

    @Column(nullable = false)
    private Long challengeId;
    public Board(String title, String nickName, String writingText, String image, String district, String bigCategory,
                 Long challengeId) {
        this.title = title;
        this.nickName = nickName;
        this.writingText = writingText;
        this.image = image;
        this.district = district;
        this.bigCategory = bigCategory;
        this.challengeId = challengeId;
    }


}

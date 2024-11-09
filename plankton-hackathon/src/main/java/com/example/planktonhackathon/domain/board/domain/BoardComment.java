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
public class BoardComment {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private Long boardId;

    @Column(nullable = false, length = 100)
    private String comment;

    @Column(nullable = false)
    private String nickName;
    public BoardComment(Long boardId, String comment, String nickName) {
        this.boardId = boardId;
        this.comment = comment;
        this.nickName = nickName;
    }
}

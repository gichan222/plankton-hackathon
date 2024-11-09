package com.example.planktonhackathon.domain.gathering.domain;


import com.example.planktonhackathon.domain.gathering.exception.GatheringErrorCode;
import com.example.planktonhackathon.global.exception.RestApiException;
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
public class Gathering {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false, length = 100)
    private String text;

    @Column(nullable = false)
    private Long challengeId;

    @Column(nullable = false)
    private int maxCount;

    @Column(nullable = false)
    private int likeCount;

    public Gathering(String nickName, String text, Long challengeId, int maxCount){
        this.nickName = nickName;
        this.text = text;
        this.challengeId = challengeId;
        this.maxCount = maxCount;
        this.likeCount = 0;
    }

    public void decreaseLikeCount(){
        this.likeCount--;
    }

    public void increaseLikeCount(){
        checkMaxCount();
        this.likeCount++;
    }

    public void checkMaxCount(){
        if(this.likeCount == this.maxCount){
            throw new RestApiException(GatheringErrorCode.FULL_GATHERING);
        }
    }
}

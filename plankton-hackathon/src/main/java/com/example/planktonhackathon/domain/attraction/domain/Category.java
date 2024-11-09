package com.example.planktonhackathon.domain.attraction.domain;

import com.example.planktonhackathon.domain.attraction.exception.AttractionErrorCode;
import com.example.planktonhackathon.global.exception.RestApiException;

public enum Category {
    SHOPPING("쇼핑", "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/category/shopping.jpg", "숨은맛집들어갈 자리입니다." , "미션내용 들어갈 자리입니다."),
    CULTURE("문화관광", "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/category/culture.jpg", "숨은맛집들어갈 자리입니다." , "미션내용 들어갈 자리입니다."),
    SPORTS("레저스포츠", "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/category/sports.jpg", "숨은맛집들어갈 자리입니다." , "미션내용 들어갈 자리입니다."),
    HISTORY("역사관광", "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/category/history.jpg", "숨은맛집들어갈 자리입니다." , "미션내용 들어갈 자리입니다."),
    NATURE("자연관광", "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/category/nature.jpg", "숨은맛집들어갈 자리입니다." , "미션내용 들어갈 자리입니다."),
    ACTIVITY("체험관광", "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/category/activity.jpg", "숨은맛집들어갈 자리입니다." , "미션내용 들어갈 자리입니다."),
    FOOD("음식","https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/category/food.jpg", "숨은맛집들어갈 자리입니다." , "미션내용 들어갈 자리입니다."),
    OTHER("기타관광","https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/category/other.jpg", "숨은맛집들어갈 자리입니다." , "미션내용 들어갈 자리입니다."),
    ;

    private final String bigCategory;
    private final String imageURL;
    private final String text;
    private final String mission;

    // 생성자
    Category(String bigCategory, String imageURL, String text, String mission) {
        this.bigCategory = bigCategory;
        this.imageURL = imageURL;
        this.text = text;
        this.mission = mission;
    }

    // Getter 메소드
    public String getBigCategory() {
        return bigCategory;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getText() {
        return text;
    }

    public String getMission() {
        return mission;
    }

    // 특정 bigCategory로 Enum을 찾는 메소드 (필요한 경우 사용)
    public static Category fromBigCategory(String bigCategory) {
        for (Category category : values()) {
            if (category.getBigCategory().equals(bigCategory)) {
                return category;
            }
        }
        throw new RestApiException(AttractionErrorCode.ATTRACTION_DOES_NOT_EXIST);
    }
}

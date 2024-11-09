package com.example.planktonhackathon.domain.mission.domain;

import com.example.planktonhackathon.domain.attraction.exception.AttractionErrorCode;
import com.example.planktonhackathon.global.exception.RestApiException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum Mission {
    쇼핑("쇼핑", List.of(
            "Take a photo of a purchase under 10,000 KRW",
            "Take a photo of a purchase over 10,000 KRW",
            "Take a photo of a purchase under 30,000 KRW",
            "Take a photo of a purchase over 30,000 KRW",
            "Take a photo of the cheapest item",
            "Take a photo of the most expensive item",
            "Take a photo of a shopping bag",
            "Take a photo of a red-colored product",
            "Take a photo of a blue-colored product",
            "Take a photo of a green-colored product",
            "Take a photo of a yellow-colored product",
            "Take a photo of a black-colored product",
            "Take a photo of a white-colored product"
    )),
    문화관광("문화관광", List.of(
            "Take a photo of a peace sign",
            "Take a photo of a traditional costume",
            "Take a photo of a ticket",
            "Take a photo of arms wide open",
            "Take a photo of a heart gesture",
            "Take a photo of food",
            "Take a photo of a map",
            "Take a photo of a thumbs-up",
            "Take a photo of a happy expression",
            "Take a photo of a selfie",
            "Take a photo of a sky background",
            "Take a photo of a solo shot",
            "Take a photo of a jump",
            "Take a photo of a friend and a heart gesture"
    )),
    레저스포츠("레저스포츠", List.of(
            "Take a photo of a peace sign",
            "Take a photo of a traditional costume",
            "Take a photo of a ticket",
            "Take a photo of arms wide open",
            "Take a photo of a heart gesture",
            "Take a photo of food",
            "Take a photo of a map",
            "Take a photo of a thumbs-up",
            "Take a photo of a happy expression",
            "Take a photo of a selfie",
            "Take a photo of a sky background",
            "Take a photo of a solo shot",
            "Take a photo of a jump",
            "Take a photo of a friend and a heart gesture"
    )),
    역사관광("역사관광", List.of(
            "Take a photo of a peace sign",
            "Take a photo of a traditional costume",
            "Take a photo of a ticket",
            "Take a photo of arms wide open",
            "Take a photo of a heart gesture",
            "Take a photo of food",
            "Take a photo of a map",
            "Take a photo of a thumbs-up",
            "Take a photo of a happy expression",
            "Take a photo of a selfie",
            "Take a photo of a sky background",
            "Take a photo of a solo shot",
            "Take a photo of a jump",
            "Take a photo of a friend and a heart gesture"
    )),
    자연관광("자연관광", List.of(
            "Take a photo of a peace sign",
            "Take a photo of a traditional costume",
            "Take a photo of a ticket",
            "Take a photo of arms wide open",
            "Take a photo of a heart gesture",
            "Take a photo of food",
            "Take a photo of a map",
            "Take a photo of a thumbs-up",
            "Take a photo of a happy expression",
            "Take a photo of a selfie",
            "Take a photo of a sky background",
            "Take a photo of a solo shot",
            "Take a photo of a jump",
            "Take a photo of a friend and a heart gesture"
    )),
    체험관광("체험관광", List.of(
            "Take a photo of a peace sign",
            "Take a photo of a traditional costume",
            "Take a photo of a ticket",
            "Take a photo of arms wide open",
            "Take a photo of a heart gesture",
            "Take a photo of food",
            "Take a photo of a map",
            "Take a photo of a thumbs-up",
            "Take a photo of a happy expression",
            "Take a photo of a selfie",
            "Take a photo of a sky background",
            "Take a photo of a solo shot",
            "Take a photo of a jump",
            "Take a photo of a friend and a heart gesture"
    )),
    음식("음식", List.of(
            "Take a photo of a purchase under 10,000 KRW",
            "Take a photo of a purchase over 10,000 KRW",
            "Take a photo of a purchase under 30,000 KRW",
            "Take a photo of a purchase over 30,000 KRW",
            "Take a photo of a menu",
            "Take a photo of a receipt",
            "Take a photo of a traditional food",
            "Take a photo of fruit",
            "Take a photo of soup-based food",
            "Take a photo of kimchi",
            "Take a photo of a dessert"
    )),
    기타관광("기타관광", List.of(
            "Take a photo of a peace sign",
            "Take a photo of a traditional costume",
            "Take a photo of a ticket",
            "Take a photo of arms wide open",
            "Take a photo of a heart gesture",
            "Take a photo of food",
            "Take a photo of a map",
            "Take a photo of a thumbs-up",
            "Take a photo of a happy expression",
            "Take a photo of a selfie",
            "Take a photo of a sky background",
            "Take a photo of a solo shot",
            "Take a photo of a jump",
            "Take a photo of a friend and a heart gesture"
    ));

    private final String name;  // 카테고리 한글 이름
    private final List<String> missions;  // 다수의 미션 텍스트

    // 생성자
    Mission(String name, List<String> missions) {
        this.name = name;
        this.missions = missions;
    }

    // Getter 메소드
    public String getName() {
        return name;
    }

    public List<String> getMissions() {
        return missions;
    }

    // 특정 카테고리의 미션을 랜덤하게 2개 선택
    public static List<String> getRandomMissionsByCategory(String bigCategory) {
        Mission category = Mission.fromBigCategory(bigCategory);  // Enum 상수 찾기
        Random random = new Random();
        List<String> selectedMissions = new ArrayList<>();
        List<String> availableMissions = new ArrayList<>(category.getMissions());  // 미션 리스트 복사

        while (selectedMissions.size() < 2 && !availableMissions.isEmpty()) {
            int randomIndex = random.nextInt(availableMissions.size());
            String mission = availableMissions.remove(randomIndex);  // 선택된 미션 제거
            selectedMissions.add(mission);  // 중복 없이 추가
        }

        return selectedMissions;
    }

    // bigCategory와 매칭되는 Enum을 반환하는 메소드 (한글로 매칭)
    public static Mission fromBigCategory(String bigCategory) {
        for (Mission mission : values()) {
            // 한글로 카테고리 이름을 비교하여 일치하는 항목을 찾음
            if (mission.getName().equals(bigCategory)) {
                return mission;
            }
        }

        // 예외 처리: 매칭되는 카테고리가 없으면 예외를 던짐
        throw new RestApiException(AttractionErrorCode.ATTRACTION_DOES_NOT_EXIST);
    }
}

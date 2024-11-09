package com.example.planktonhackathon.domain.mission.domain;

import com.example.planktonhackathon.domain.attraction.exception.AttractionErrorCode;
import com.example.planktonhackathon.global.exception.RestApiException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum Mission {
    쇼핑("쇼핑", List.of("숨은 맛집 들어갈 자리입니다.", "쇼핑의 즐거움을 느껴보세요!", "이곳에서 특별한 쇼핑을 즐기세요.", "세련된 아이템을 만나보세요.", "여기에서 쇼핑을 즐겨보세요.")),
    문화관광("문화관광", List.of("문화의 향기를 느껴보세요.", "역사를 따라가보세요.", "전통을 체험해보세요.", "예술의 세계로 빠져보세요.", "문화유산을 탐방해보세요.")),
    레저스포츠("레저스포츠", List.of("스포츠의 짜릿한 순간을 경험해보세요.", "액티브한 하루를 시작하세요.", "운동으로 활력을 찾으세요.", "레저 스포츠를 즐겨보세요.", "스포츠의 매력을 느껴보세요.")),
    역사관광("역사관광", List.of("역사를 돌아보세요.", "과거로 떠나는 여행", "역사적인 순간을 경험하세요.", "과거의 유적지를 탐방하세요.", "역사를 배우고, 느껴보세요.")),
    자연관광("자연관광", List.of("자연의 아름다움을 느껴보세요.", "자연 속에서 힐링하세요.", "산과 바다를 누비는 여행", "자연의 소리를 들어보세요.", "푸르른 자연 속으로 떠나보세요.")),
    체험관광("체험관광", List.of("활동적인 체험을 해보세요.", "모험을 즐기세요.", "새로운 체험을 도전해보세요.", "체험을 통해 배우세요.", "액티비티로 즐거움을 만끽하세요.")),
    음식("음식", List.of("맛있는 음식을 즐겨보세요.", "미식의 세계로 떠나보세요.", "추천 맛집을 찾아보세요.", "전통 음식의 매력을 느껴보세요.", "이 지역의 특별한 음식을 맛보세요.")),
    기타관광("기타관광", List.of("다양한 관광지를 탐방하세요.", "기타 여행지에서 특별한 경험을 해보세요.", "새로운 장소를 발견하세요.", "자연과 도시를 동시에 즐기세요.", "이색적인 관광지를 찾으세요."));

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

package com.example.planktonhackathon.domain.attraction.service;

import static com.example.planktonhackathon.domain.mission.domain.Mission.getRandomMissionsByCategory;

import com.example.planktonhackathon.domain.attraction.domain.Attraction;
import com.example.planktonhackathon.domain.attraction.domain.Category;
import com.example.planktonhackathon.domain.attraction.exception.AttractionErrorCode;
import com.example.planktonhackathon.domain.attraction.repository.AttractionRepository;
import com.example.planktonhackathon.domain.attraction.response.AttractionCategoryResponse;
import com.example.planktonhackathon.domain.attraction.response.AttractionChallengeResponse;
import com.example.planktonhackathon.domain.mission.domain.MissionEntity;
import com.example.planktonhackathon.domain.mission.repository.MissionRepository;
import com.example.planktonhackathon.global.exception.RestApiException;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionRepository attractionRepository;
    private final MissionRepository missionRepository;
    private List<AttractionCategoryResponse> randomAttractions = new ArrayList<>();

    // 구별로 무작위로 첫 번째 Attraction을 선택
    @Transactional
    public List<AttractionCategoryResponse> getRandomAttractionsByDistrict(String district, String bigCategory) {
        // 필터링된 결과 리스트
        List<AttractionCategoryResponse> filteredAttractions = new ArrayList<>(randomAttractions);

        // district 파라미터가 있으면 필터링
        if (district != null && !district.trim().isEmpty()) {
            filteredAttractions = filteredAttractions.stream()
                    .filter(attraction -> attraction.getDistrict().trim().equalsIgnoreCase(district.trim()))
                    .collect(Collectors.toList());
        }

        // bigCategory 파라미터가 있으면 필터링
        if (bigCategory != null && !bigCategory.trim().isEmpty()) {
            filteredAttractions = filteredAttractions.stream()
                    .filter(attraction -> attraction.getBigCategory().trim().equalsIgnoreCase(bigCategory.trim()))
                    .collect(Collectors.toList());
        }

        return filteredAttractions;  // 필터링된 리스트 반환
    }



    // 메서드 내 수정된 부분
    @Transactional
    public AttractionChallengeResponse getRandomAttractionsByDistrictAndCategory(String district, String bigCategory) {
        // 특정 구와 카테고리로 Attraction 목록 조회
        List<Attraction> allAttractions = attractionRepository.findByDistrictAndBigCategory(district, bigCategory);

        if (allAttractions.isEmpty()) {
            throw new RestApiException(AttractionErrorCode.ATTRACTION_DOES_NOT_EXIST);
        }

        // Attraction 리스트 무작위로 섞기 후 상위 10개 선택
        Collections.shuffle(allAttractions);
        List<Attraction> selectedAttractions = allAttractions.size() > 10
                ? allAttractions.subList(0, 10)
                : allAttractions;

        // Category 정보 가져오기
        Category category = Category.fromBigCategory(bigCategory);

        // AttractionChallengeListResponse 객체로 반환
        return new AttractionChallengeResponse(
                category.getImageURL(),
                category.getText(),
                category.getMission(),
                selectedAttractions
        );
    }



    private void saveRandomAttractions() {
        // 모든 Attraction을 가져오기
        List<Attraction> allAttractions = attractionRepository.findAll();
        if (allAttractions.isEmpty()) {
            throw new RestApiException(AttractionErrorCode.ATTRACTION_DOES_NOT_EXIST);
        }

        // 구별로 Attraction 분류
        Map<String, List<Attraction>> districtMap = new HashMap<>();
        for (Attraction attraction : allAttractions) {
            districtMap.computeIfAbsent(attraction.getDistrict(), k -> new ArrayList<>()).add(attraction);
        }

        // 구별로 무작위로 하나의 Attraction을 선택
        List<AttractionCategoryResponse> selectedAttractions = new ArrayList<>();
        Random random = new Random();

        for (Map.Entry<String, List<Attraction>> entry : districtMap.entrySet()) {
            List<Attraction> districtAttractions = entry.getValue();
            int randomIndex = random.nextInt(districtAttractions.size());
            Attraction selectedAttraction = districtAttractions.get(randomIndex);

            // 카테고리 정보 가져오기
            String bigCategory = selectedAttraction.getBigCategory();
            String text = Category.fromBigCategory(bigCategory) != null
                    ? Category.fromBigCategory(bigCategory).getText()
                    : "기본 텍스트";  // null 처리

            // district, bigCategory, text를 포함한 AttractionCategoryResponse 생성
            AttractionCategoryResponse attractionCategoryResponse = new AttractionCategoryResponse(
                    selectedAttraction.getDistrict(),
                    bigCategory,
                    text
            );
            selectedAttractions.add(attractionCategoryResponse);

            List<String> missions = getRandomMissionsByCategory(bigCategory);
            System.out.println(missionRepository.existsByDistrictAndLocalDate(selectedAttraction.getDistrict(),
                    LocalDate.now()));
            System.out.println(selectedAttraction.getDistrict() + " " + selectedAttraction.getBigCategory());
            if(missionRepository.existsByDistrictAndLocalDate(selectedAttraction.getDistrict(),
                    LocalDate.now())){
                continue;
            }
            for(String s : missions){
                MissionEntity missionEntity = new MissionEntity(selectedAttraction.getDistrict(),selectedAttraction.getBigCategory(),s);
                missionRepository.save(missionEntity);
            }

        }


        // 선택된 AttractionResponse 리스트를 저장
        this.randomAttractions = selectedAttractions;
    }


//     프로그램 시작 시 한 번 실행
    @PostConstruct
    private void initialize() {
        log.info("시작 시 챌린지 초기화 시작");
        saveRandomAttractions();  // 초기화 시 무작위 Attraction 저장
    }

    // 매일 밤 00시에 자동 실행
    @Scheduled(cron = "0 0 0 * * ?")  // 매일 밤 00시 실행
    protected void scheduleAttractionsUpdate() {
        log.info("00시 챌린지 초기화 시작");
        saveRandomAttractions();  // 매일 밤 업데이트
    }



}

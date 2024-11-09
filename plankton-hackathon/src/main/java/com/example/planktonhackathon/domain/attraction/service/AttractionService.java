package com.example.planktonhackathon.domain.attraction.service;

import com.example.planktonhackathon.domain.attraction.domain.Attraction;
import com.example.planktonhackathon.domain.attraction.exception.AttractionErrorCode;
import com.example.planktonhackathon.domain.attraction.repository.AttractionRepository;
import com.example.planktonhackathon.domain.attraction.response.AttractionCategoryResponse;
import com.example.planktonhackathon.domain.attraction.response.AttractionChallengeResponse;
import com.example.planktonhackathon.global.exception.RestApiException;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionRepository attractionRepository;
    private List<AttractionCategoryResponse> randomAttractions = new ArrayList<>();

    // 구별로 무작위로 첫 번째 Attraction을 선택
    public List<AttractionCategoryResponse> getRandomAttractionsByDistrict() {
        return randomAttractions;  // 저장된 결과를 반환
    }

    public List<AttractionChallengeResponse> getRandomAttractionsByDistrictAndCategory(String district, String bigCategory) {
        // 특정 구와 카테고리로 모든 Attraction 가져오기
        List<Attraction> allAttractions = attractionRepository.findByDistrictAndBigCategory(district, bigCategory);

        if (allAttractions.isEmpty()) {
            throw new RestApiException(AttractionErrorCode.ATTRACTION_DOES_NOT_EXIST);
        }

        // 리스트 무작위로 섞기
        Collections.shuffle(allAttractions);

        // 상위 10개만 선택 (리스트의 크기가 10 미만이면 전체 리스트 반환)
        List<Attraction> selectedAttractions = allAttractions.size() > 10
                ? allAttractions.subList(0, 10)
                : allAttractions;

        // 선택된 Attraction을 AttractionChallengeResponse 형태로 변환
        List<AttractionChallengeResponse> responses = new ArrayList<>();
        for (Attraction attraction : selectedAttractions) {
            responses.add(new AttractionChallengeResponse(attraction));
        }

        return responses;
    }



    // 구별로 무작위로 첫 번째 Attraction을 선택하고 저장
    private void saveRandomAttractions() {
        // 모든 Attraction을 가져오기
        List<Attraction> allAttractions = attractionRepository.findAll();
        if(allAttractions.isEmpty()){
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

            // district와 bigCategory만 포함된 AttractionResponse 생성
            selectedAttractions.add(new AttractionCategoryResponse(selectedAttraction.getDistrict(), selectedAttraction.getBigCategory()));
        }

        // 선택된 AttractionResponse 리스트를 저장
        this.randomAttractions = selectedAttractions;
    }

    // 프로그램 시작 시 한 번 실행
    @PostConstruct
    private void initialize() {
        log.info("시작 시 챌린지 초기화 시작");
        saveRandomAttractions();  // 초기화 시 무작위 Attraction 저장
    }

    // 매일 밤 00시에 자동 실행
    @Scheduled(cron = "0 0 0 * * ?")  // 매일 밤 00시 실행
    private void scheduleAttractionsUpdate() {
        log.info("00시 챌린지 초기화 시작");
        saveRandomAttractions();  // 매일 밤 업데이트
    }
}

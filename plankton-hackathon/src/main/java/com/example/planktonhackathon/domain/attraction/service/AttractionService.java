package com.example.planktonhackathon.domain.attraction.service;

import com.example.planktonhackathon.domain.attraction.domain.Attraction;
import com.example.planktonhackathon.domain.attraction.exception.AttractionErrorCode;
import com.example.planktonhackathon.domain.attraction.repository.AttractionRepository;
import com.example.planktonhackathon.domain.attraction.response.AttractionResponse;
import com.example.planktonhackathon.global.exception.RestApiException;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
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
    private List<Attraction> randomAttractions = new ArrayList<>();

    // 구별로 무작위로 첫 번째 Attraction을 선택
    public AttractionResponse getRandomAttractionsByDistrict() {
        return new AttractionResponse(randomAttractions);  // 이미 저장된 결과를 반환
    }

    // 구별로 무작위로 첫 번째 Attraction을 선택하고 저장
    private void saveRandomAttractions() {
        // 모든 Attraction을 가져오기
        List<Attraction> allAttractions = attractionRepository.findAll();
        if(allAttractions.isEmpty()){
            throw new RestApiException(AttractionErrorCode.ATTRACTION_DOES_NOT_EXIST);
        }

        // 구별로 무작위로 하나의 Attraction만 선택하기 위한 Map
        Map<String, List<Attraction>> districtMap = new HashMap<>();

        // 구별로 Attraction 분류
        for (Attraction attraction : allAttractions) {
            districtMap.computeIfAbsent(attraction.getDistrict(), k -> new ArrayList<>()).add(attraction);
        }

        // 구별로 무작위로 하나의 Attraction을 선택
        List<Attraction> selectedAttractions = new ArrayList<>();
        Random random = new Random();

        for (Map.Entry<String, List<Attraction>> entry : districtMap.entrySet()) {
            List<Attraction> districtAttractions = entry.getValue();
            int randomIndex = random.nextInt(districtAttractions.size());
            selectedAttractions.add(districtAttractions.get(randomIndex));
        }

        // 선택된 Attraction을 저장
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
        log.info("00사 챌린지 초기화 시작");
        saveRandomAttractions();  // 매일 밤 업데이트
    }
}

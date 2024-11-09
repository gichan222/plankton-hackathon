package com.example.planktonhackathon.domain.attraction.controller;

import com.example.planktonhackathon.domain.attraction.response.AttractionCategoryResponse;
import com.example.planktonhackathon.domain.attraction.response.AttractionChallengeResponse;
import com.example.planktonhackathon.domain.attraction.service.AttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "챌린지 API", description = "챌린지 정보 대한 처리를 담당하는 API입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge")
public class AttractionController {

    private final AttractionService attractionService;

    @Operation(summary = "해당 일자의 챌린지 정보를 제공하는 API입니다.")
    @GetMapping
    public ResponseEntity<List<AttractionCategoryResponse>> getAttractionList(
            @RequestParam(required = false) String district,    // district 파라미터, 선택 사항
            @RequestParam(required = false) String bigCategory // bigCategory 파라미터, 선택 사항
    ) {
        List<AttractionCategoryResponse> attractionChallengeResponse = attractionService.getRandomAttractionsByDistrict(district, bigCategory);
        return ResponseEntity.ok().body(attractionChallengeResponse);
    }


    @Operation(summary = "구와 BigCategory를 기반으로 10개의 Attraction 리스트를 반환합니다.")
    @GetMapping("/attractions-by-district-category")
    public ResponseEntity<AttractionChallengeResponse> getAttractionsByDistrictAndCategory(
            @RequestParam String district,
            @RequestParam String bigCategory) {
        AttractionChallengeResponse attractionResponses = attractionService.getRandomAttractionsByDistrictAndCategory(district, bigCategory);
        return ResponseEntity.ok().body(attractionResponses);
    }

}

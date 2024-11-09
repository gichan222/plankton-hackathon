package com.example.planktonhackathon.domain.attraction.controller;


import com.example.planktonhackathon.domain.attraction.response.AttractionResponse;
import com.example.planktonhackathon.domain.attraction.service.AttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "챌린지 API", description = "챌린지 정보 대한 처리를 담당하는 API입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge")
public class AttractionController {

    private final AttractionService attractionService;

    @Operation(summary = "해당 일자의 챌린지 정보를 제공하는 API입니다.")
    @GetMapping
    public ResponseEntity<AttractionResponse> getAttractionList(){
        AttractionResponse attractionResponse = attractionService.getRandomAttractionsByDistrict();
        return ResponseEntity.ok().body(attractionResponse);
    }
}

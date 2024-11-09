package com.example.planktonhackathon.domain.mission.controller;

import com.example.planktonhackathon.domain.mission.response.MissionResponse;
import com.example.planktonhackathon.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // district와 bigCategory를 파라미터로 받아서 Mission 정보 반환
    @GetMapping("/mission")
    public ResponseEntity<MissionResponse> getMissionInfo(@RequestParam String district, @RequestParam String bigCategory) {
        return ResponseEntity.ok().body(missionService.getMissionInfo(district, bigCategory));
    }
}

package com.example.planktonhackathon.domain.mission.controller;

import com.example.planktonhackathon.domain.mission.response.MissionResponse;
import com.example.planktonhackathon.domain.mission.service.MissionService;
import com.example.planktonhackathon.global.auth.annotation.Auth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "미션 API", description = "미션에 대한 처리를 담당하는 API입니다")
@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // district와 bigCategory를 파라미터로 받아서 Mission 정보 반환
    @Auth
    @Operation(summary = "미션 참여 및 팀에 대한 정보를 제공하는 api입니다.")
    @GetMapping("/mission")
    public ResponseEntity<MissionResponse> getMissionInfo(@RequestParam String district, @RequestParam String bigCategory) {
        return ResponseEntity.ok().body(missionService.getMissionInfo(district, bigCategory));
    }
}

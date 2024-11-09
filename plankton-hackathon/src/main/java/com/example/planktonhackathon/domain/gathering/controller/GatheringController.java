package com.example.planktonhackathon.domain.gathering.controller;

import com.example.planktonhackathon.domain.gathering.request.GatheringWriteRequest;
import com.example.planktonhackathon.domain.gathering.response.GatheringGetResponse;
import com.example.planktonhackathon.domain.gathering.service.GatheringService;
import com.example.planktonhackathon.global.auth.annotation.Auth;
import com.example.planktonhackathon.global.common.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "챌린지 구인 API", description = "챌린지 구인에 대한 처리를 담당하는 API입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/gathering")
public class GatheringController {

    private final GatheringService gatheringService;

    @Auth
    @Operation(summary = "챌린지 구인 글 작성 API")
    @PostMapping
    public ResponseEntity<SuccessResponse> writeGathering(@Valid @RequestBody GatheringWriteRequest gatheringWriteRequest){
        gatheringService.writeGathering(gatheringWriteRequest);
        return ResponseEntity.ok(new SuccessResponse());
    }

    @Auth
    @Operation(summary = "챌린지 구인 글 GET API")
    @GetMapping
    public ResponseEntity<List<GatheringGetResponse>> getGathering(@RequestParam Long challengeId){
        return ResponseEntity.ok().body(gatheringService.getGathering(challengeId));
    }

    @Auth
    @Operation(summary = "조인 등록/취소 초과 검사 API")
    @GetMapping("/join")
    public ResponseEntity<SuccessResponse> joinGathering(@RequestParam Long id){
        gatheringService.joinGathering(id);
        return ResponseEntity.ok(new SuccessResponse());
    }
}

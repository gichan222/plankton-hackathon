package com.example.planktonhackathon.global.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    // /healthcheck 경로로 GET 요청을 처리하고, 상태 코드 200을 반환
    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK) // 응답 상태 코드 200을 반환
    public void healthCheck() {
        // 여기서 특별한 내용 없이 상태 코드 200만 반환됨
    }
}

package com.example.planktonhackathon.domain;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")  // 기본 경로 설정
public class TestController {

    // GET 메서드 예시: 특정 ID에 해당하는 리소스를 조회
    @GetMapping("/{id}")
    public String getTest(@PathVariable Long id) {
        return "GET 요청 - ID: " + id;
    }

    // POST 메서드 예시: 새로운 리소스를 생성
    @PostMapping
    public String createTest(@RequestBody String requestBody) {
        return "POST 요청 - 데이터: " + requestBody;
    }

    // PUT 메서드 예시: 특정 ID에 해당하는 리소스를 업데이트
    @PutMapping("/{id}")
    public String updateTest(@PathVariable Long id, @RequestBody String requestBody) {
        return "PUT 요청 - ID: " + id + ", 데이터: " + requestBody;
    }

    // DELETE 메서드 예시: 특정 ID에 해당하는 리소스를 삭제
    @DeleteMapping("/{id}")
    public String deleteTest(@PathVariable Long id) {
        return "DELETE 요청 - ID: " + id;
    }
}

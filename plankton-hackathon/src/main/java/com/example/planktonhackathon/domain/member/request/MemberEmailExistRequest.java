package com.example.planktonhackathon.domain.member.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MemberEmailExistRequest {
    @NotNull(message = "이메일은 필수 입력입니다.")
    private String email;
}

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
public class MemberLoginRequest {
    @NotNull(message = "이메일은 필수 입력입니다.")
    private String email;
    @NotNull(message = "비밀번호는 필수 입력입니다.")
    private String password;
}

package com.example.planktonhackathon.domain.member.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberBadgeResponse {

    String badge;
    String imageUrl;
}

package com.example.planktonhackathon.domain.attraction.response;

import com.example.planktonhackathon.domain.attraction.domain.Attraction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AttractionChallengeResponse {

    private final Attraction attraction;
}

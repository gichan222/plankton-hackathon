package com.example.planktonhackathon.domain.attraction.response;

import com.example.planktonhackathon.domain.attraction.domain.Attraction;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AttractionChallengeResponse {
    private final String imageURL;
    private final String text;
    private final String mission;
    private final List<Attraction> attractions;
}

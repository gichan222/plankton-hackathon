package com.example.planktonhackathon.domain.attraction.response;


import com.example.planktonhackathon.domain.attraction.domain.Attraction;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AttractionResponse {

    private List<Attraction> allAttactions;
}

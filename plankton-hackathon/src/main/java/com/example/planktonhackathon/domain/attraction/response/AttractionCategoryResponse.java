package com.example.planktonhackathon.domain.attraction.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AttractionCategoryResponse {

    private final String district;
    private final String bigCategory;
}

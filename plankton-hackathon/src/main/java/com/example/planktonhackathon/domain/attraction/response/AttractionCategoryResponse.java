package com.example.planktonhackathon.domain.attraction.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class AttractionCategoryResponse {
    private String district;
    private String bigCategory;
    private String text;

}


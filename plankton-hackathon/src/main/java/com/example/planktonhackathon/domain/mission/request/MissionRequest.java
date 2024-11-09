package com.example.planktonhackathon.domain.mission.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MissionRequest {

    private String district;
    private String bigCategory;
}

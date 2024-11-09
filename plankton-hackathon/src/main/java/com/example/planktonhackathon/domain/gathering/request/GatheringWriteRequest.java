package com.example.planktonhackathon.domain.gathering.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GatheringWriteRequest {

    @NotNull
    private String text;
    @NotNull
    private int maxCount;
}

package com.example.planktonhackathon.domain.gathering.response;

import com.example.planktonhackathon.domain.gathering.domain.Gathering;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GatheringGetResponse {

    private List<Gathering> gatherings;
}

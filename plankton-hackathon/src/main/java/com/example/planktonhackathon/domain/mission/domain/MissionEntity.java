package com.example.planktonhackathon.domain.mission.domain;


import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class MissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String bigCategory;

    @Column(nullable = false)
    private String missionText;

    @Column(nullable = false)
    private LocalDate localDate;

    public MissionEntity(String district, String bigCategory, String missionText) {
        this.district = district;
        this.bigCategory = bigCategory;
        this.missionText = missionText;
        this.localDate = LocalDate.now();
    }
}

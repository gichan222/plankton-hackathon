package com.example.planktonhackathon.domain.attraction.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Attraction {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String attraction;

    @Column(nullable = false)
    private String address;


    @Column(nullable = false)
    private String bigCategory;

    @Column(nullable = false)
    private String smallCategory;
    public Attraction(String district, String attraction, String address, String bigCategory, String smallCategory) {
        this.district = district;
        this.attraction = attraction;
        this.address = address;
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
    }


}

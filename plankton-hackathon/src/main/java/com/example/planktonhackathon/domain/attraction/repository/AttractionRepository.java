package com.example.planktonhackathon.domain.attraction.repository;

import com.example.planktonhackathon.domain.attraction.domain.Attraction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    List<Attraction> findByDistrictAndBigCategory(String district, String bigCategory);
}

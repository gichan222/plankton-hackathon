package com.example.planktonhackathon.domain.mission.repository;

import com.example.planktonhackathon.domain.mission.domain.MissionEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<MissionEntity, Long> {

    boolean existsByDistrictAndLocalDate(String district, LocalDate localDate);
    void deleteByDistrictAndBigCategoryAndLocalDate(String district, String bigCategory, LocalDate localDate);

    List<MissionEntity> findAllByDistrictAndBigCategoryAndLocalDate(String district, String bigCategory, LocalDate localDate);
}
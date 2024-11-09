package com.example.planktonhackathon.domain.attraction.repository;

import com.example.planktonhackathon.domain.attraction.domain.Attraction;
import com.example.planktonhackathon.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {

}

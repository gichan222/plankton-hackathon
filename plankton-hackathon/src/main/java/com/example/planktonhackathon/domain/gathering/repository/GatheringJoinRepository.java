package com.example.planktonhackathon.domain.gathering.repository;

import com.example.planktonhackathon.domain.gathering.domain.GatheringJoin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatheringJoinRepository extends JpaRepository<GatheringJoin, Long> {

    boolean existsByEmailAndChallengeId(String email, Long challengeId);
    void deleteByEmailAndChallengeId(String email, Long challengeId);
}

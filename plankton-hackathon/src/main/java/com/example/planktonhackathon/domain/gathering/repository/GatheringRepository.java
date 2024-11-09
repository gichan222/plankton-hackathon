package com.example.planktonhackathon.domain.gathering.repository;

import com.example.planktonhackathon.domain.gathering.domain.Gathering;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatheringRepository extends JpaRepository<Gathering, Long> {
    boolean existsByNickNameAndChallengeId(String nickName, Long challengeId);
    List<Gathering> findByChallengeId(Long challengeId);
    Optional<Gathering> findByNickNameAndChallengeId(String nickName, Long challengeId);
    Optional<Gathering> findById(Long id);
}

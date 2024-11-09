package com.example.planktonhackathon.domain.board.repository;

import com.example.planktonhackathon.domain.board.domain.Board;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findById(Long id);

    List<Board> findByChallengeId(Long challengeId);
}

package com.example.planktonhackathon.domain.board.repository;

import com.example.planktonhackathon.domain.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}

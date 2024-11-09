package com.example.planktonhackathon.domain.board.repository;

import com.example.planktonhackathon.domain.board.domain.BoardComment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

    List<BoardComment> findByBoardId(Long boardId);
}

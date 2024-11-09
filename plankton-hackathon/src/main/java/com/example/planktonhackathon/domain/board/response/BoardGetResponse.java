package com.example.planktonhackathon.domain.board.response;

import com.example.planktonhackathon.domain.board.domain.Board;
import com.example.planktonhackathon.domain.board.domain.BoardComment;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardGetResponse {

    private Board board;
    private List<BoardComment> boardComments;
}

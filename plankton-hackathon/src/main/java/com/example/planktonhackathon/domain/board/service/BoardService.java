package com.example.planktonhackathon.domain.board.service;

import com.example.planktonhackathon.domain.board.domain.Board;
import com.example.planktonhackathon.domain.board.domain.BoardComment;
import com.example.planktonhackathon.domain.board.exception.BoardErrorCode;
import com.example.planktonhackathon.domain.board.repository.BoardCommentRepository;
import com.example.planktonhackathon.domain.board.repository.BoardRepository;
import com.example.planktonhackathon.domain.board.request.BoardCommentWriteRequest;
import com.example.planktonhackathon.domain.board.request.BoardWriteRequest;
import com.example.planktonhackathon.domain.board.response.BoardGetResponse;
import com.example.planktonhackathon.domain.member.domain.Member;
import com.example.planktonhackathon.domain.member.domain.MemberBadge;
import com.example.planktonhackathon.domain.member.exception.MemberErrorCode;
import com.example.planktonhackathon.domain.member.repository.MemberBadgeRepository;
import com.example.planktonhackathon.domain.member.repository.MemberRepository;
import com.example.planktonhackathon.global.auth.exception.AuthErrorCode;
import com.example.planktonhackathon.global.auth.service.AuthMemberService;
import com.example.planktonhackathon.global.exception.RestApiException;
import com.example.planktonhackathon.global.s3.service.S3Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;
    private final MemberRepository memberRepository;
    private final MemberBadgeRepository memberBadgeRepository;
    private final AuthMemberService authMemberService;
    private final S3Service s3Service;

    @Transactional
    public void writeBoard(BoardWriteRequest boardWriteRequest) throws IOException {
        Member member = memberRepository.findByEmail(authMemberService.getMemberEmail())
                .orElseThrow(() -> new RestApiException(AuthErrorCode.NO_USER_INFO));
        String image = s3Service.uploadFile(boardWriteRequest.getMultipartFile(), member.getEmail());
        Board board = new Board(boardWriteRequest.getTitle(), member.getNickName(), boardWriteRequest.getText(), image,
                boardWriteRequest.getDistrict(), boardWriteRequest.getBigCategory(), member.getChallengeId());
        member.increaseChallengeCount();
        member.levelUpCheck();
        if(!memberBadgeRepository.existsByEmailAndDistrict(member.getEmail(), board.getDistrict())){
            MemberBadge memberBadge = new MemberBadge(member.getEmail(),board.getDistrict());
            memberBadgeRepository.save(memberBadge);
        }
        if(memberBadgeRepository.countByEmail(member.getEmail()) == 25){
            MemberBadge memberBadge = new MemberBadge(member.getEmail(),"서울");
            memberBadgeRepository.save(memberBadge);
        }
        boardRepository.save(board);
    }

    @Transactional
    public void writeComment(BoardCommentWriteRequest boardCommentWriteRequest){
        Member member = memberRepository.findByEmail(authMemberService.getMemberEmail())
                .orElseThrow(() -> new RestApiException(AuthErrorCode.NO_USER_INFO));
        Board boardEntity = boardRepository.findById(boardCommentWriteRequest.getBoardId()).orElseThrow(() -> new RestApiException(BoardErrorCode.BOARD_NOT_FOUND));
        if (member.getChallengeId() != boardEntity.getChallengeId()) {
            throw new RestApiException(MemberErrorCode.TEAM_DOES_NOT_MATCH);
        }
        BoardComment boardComment = new BoardComment(boardCommentWriteRequest.getBoardId(),
                boardCommentWriteRequest.getComment(), member.getNickName());
        boardCommentRepository.save(boardComment);
    }

    @Transactional
    public List<BoardGetResponse> getBoard() {
        Member member = memberRepository.findByEmail(authMemberService.getMemberEmail())
                .orElseThrow(() -> new RestApiException(AuthErrorCode.NO_USER_INFO));

        if(member.getChallengeId() == null){
            throw new RestApiException(MemberErrorCode.TEAM_DOES_NOT_MATCH);
        }
        List<Board> boards = boardRepository.findByChallengeId(member.getChallengeId());

        if (boards.isEmpty()) {
            return new ArrayList<>();
        }

        List<BoardGetResponse> boardGetResponses = boards.stream()
                .map(board -> {
                    List<BoardComment> boardComments = boardCommentRepository.findByBoardId(board.getId());
                    return new BoardGetResponse(board, boardComments);
                })
                .collect(Collectors.toList());

        return boardGetResponses;
    }


}

package com.ktds.kart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ktds.kart.dto.BoardDto;
import com.ktds.kart.entity.Board;
import com.ktds.kart.repository.BoardRepository;

@Service
public class BoardService {

    private BoardRepository boardRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 수
    private static final int PAGE_POST_COUNT = 5; // 한페이지에 게시글 수

    @Transactional
    public List<BoardDto> searchPosts(String keyword) {
        List<Board> boards = boardRepository.findByTitleContaining(keyword);
        List<BoardDto> boardDtoList = new ArrayList<>();

        if(boards.isEmpty()) return boardDtoList;

        for(Board board : boards) {
            boardDtoList.add(this.convertEntityToDto(board));
        }

        return boardDtoList;
    }

    private BoardDto convertEntityToDto(Board board) {
        return BoardDto.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .price(board.getPrice())
                .nickname(board.getNickname())
                .reg_date(board.getReg_date())
                .build();
    }

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getBno();
        }

    @Transactional
    public List<BoardDto> getBoardlist(Integer pageNum) {

        Page<Board> page = boardRepository
                .findAll(PageRequest
                        .of(pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "bno")));
        // List<Board> boards = boardRepository.findAll();
        List<Board> boards = page.getContent();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boards) {
            BoardDto boardDto = BoardDto.builder()
                                .bno(board.getBno())
                                .title(board.getTitle())
                                .content(board.getContent())
                                .price(board.getPrice())
                                .nickname(board.getNickname())
                                .reg_date(board.getReg_date())
                                .build();

            boardDtoList.add(boardDto);
        }

        return boardDtoList;
    }
    @Transactional
    public BoardDto getPost(Long bno) {
        Optional<Board> boardWrapper = boardRepository.findById(bno);
        Board board = boardWrapper.get();

        BoardDto boardDto = BoardDto.builder()
                            .bno(board.getBno())
                            .title(board.getTitle())
                            .content(board.getContent())
                            .price(board.getPrice())
                            .nickname(board.getNickname())
                            .reg_date(board.getReg_date())
                            .build();
        
        return boardDto;
    }

    @Transactional
    public void deletePost(Long bno) {
        boardRepository.deleteById(bno);
    }

    @Transactional
    public void delete(Long bno){
        Board board = boardRepository.findById(bno)
                .orElseThrow(()->new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        boardRepository.delete(board);
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        //총 게시글 수
        Double postsTotalCount = Double.valueOf(this.getBoardCount());

        // 총 게시글 수를 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum<=3) ? 1 : curPageNum-2;

        //페이지 번호 할당
        for(int val=curPageNum, i=0;val<=blockLastPageNum;val++, i++) {
            pageList[i] = val;
        }

        return pageList;
    }

    @Transactional
    public Long getBoardCount() {
        return boardRepository.count();
    }

    @Transactional
    public Long update(Long bno, BoardDto boardDto) {
        Board board = boardRepository.findById(bno)
        	.orElseThrow(() -> new
            IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        board.update(boardDto.getTitle(),
                boardDto.getContent(),
                boardDto.getPrice());

        return bno;
    }

    //view counting
    @Transactional
    public int updateView(Long bno) {
        return boardRepository.updateView(bno);
    }
    }

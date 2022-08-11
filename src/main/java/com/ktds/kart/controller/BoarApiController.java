package com.ktds.kart.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ktds.kart.dto.BoardDto;
import com.ktds.kart.entity.Board;
import com.ktds.kart.repository.BoardRepository;
import com.ktds.kart.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoarApiController {
    
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    //생성
    @PostMapping("/create")
    public ResponseEntity<?> saveBoard(@RequestBody Board board) {
        Board savedBoard = boardRepository.save(board);
        return ResponseEntity.ok(savedBoard);
    }
    // 목록 조회
    @GetMapping("/look")
    public List<Board> all() {
        return boardRepository.findAll();
    }

    // 단건 조회
    @GetMapping("/look/{bno}")
    public Board getBoard(@PathVariable Long bno) {
        return boardRepository.findById(bno)
                .orElseThrow(() -> new IllegalArgumentException("illegal argument: " + bno));
    }

    // 수정
    
  	//수정 
    // @PutMapping("/{bno}")
    // public void updateBoard(@PathVariable Long bno, @RequestBody Board newBoard) {
    //     boardRepository.findById(bno)
    //             .map(board -> {
    //                 board.setTitle(newBoard.getTitle());
    //                 board.setContent(newBoard.getContent());

    //                 return boardRepository.save(board);
    //             })
    //             .orElseGet(() -> {
    //                 newBoard.setBno(bno);
    //                 return boardRepository.save(newBoard);
    //             });

    // @PutMapping("/look/edit/{bno}")
	// public ResponseEntity<?> updateBoard(@PathVariable("bno") Long bno,  @RequestBody Board board, BoardDto boardDto) {
    //     boardRepository.findById(bno);
    //     Board updatedBoard = boardService.savePost(updatedBoard);
    //     return new ResponseEntity.ok(updatedBoard);



}

package com.ktds.kart.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
// @CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
@RequestMapping("/board")
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
    @GetMapping("/list")
    public List<Board> all() {
        return boardRepository.findAll();
    }

    // 단건 조회
    @GetMapping("/list/{bno}")
    public Board getBoard(@PathVariable("bno") Long bno, Model model) {
        BoardDto boardDto = boardService.getPost(bno);
        boardService.updateView(bno);
        model.addAttribute("boardDto",boardDto);
        return boardRepository.findById(bno)
                .orElseThrow(() -> new IllegalArgumentException("illegal argument: " + bno));
    }

    // 수정
    @PutMapping("/list/{bno}")
    public Long updateBoard(@PathVariable("bno") Long bno, @RequestBody BoardDto boardDto) {
        return boardService.update(bno, boardDto);
    }

    //삭제
    @DeleteMapping("/list/{bno}")
    public void delet(@PathVariable Long bno){
        boardService.delete(bno);
    }





}

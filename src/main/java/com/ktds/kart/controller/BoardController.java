package com.ktds.kart.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ktds.kart.dto.BoardDto;
import com.ktds.kart.entity.Board;
import com.ktds.kart.repository.BoardRepository;
import com.ktds.kart.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
// @RequestMapping("/homepg")
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    
    // @GetMapping("/list")
    // public String list(Model model) {
    //     List<BoardDto> boardDtoList = boardService.getBoardlist();
    //     model.addAttribute("boardList", boardDtoList);
        
    //     return "board/list.html";
    // }
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardDtoList = boardService.getBoardlist(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardDtoList);
        model.addAttribute("pageList", pageList);

        return "/board/list";
    }

    @GetMapping("/post")
    public String write() {
        return "board/write.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/list";
    }

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long bno, Model model) {
        BoardDto boardDto = boardService.getPost(bno);
        boardService.updateView(bno);
        model.addAttribute("boardDto",boardDto);
        return "board/detail.html";
    }

    @GetMapping("post/edit/{no}")
    public String edit(@PathVariable("no") Long bno, Model model) {
        BoardDto boardDto = boardService.getPost(bno);

        model.addAttribute("boardDto",boardDto);

        return "board/update.html";
    }

    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/list";
    }

    @DeleteMapping("post/{no}")
    public String delete(@PathVariable("no") Long bno) {
        boardService.deletePost(bno);
        return "redirect:/list";
    }

    @GetMapping("/board/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);
        model.addAttribute("boardList", boardDtoList);

        return "board/list.html";
    }

    // @GetMapping("/post/{bno}")
    // public String read(@PathVariable("bno") Long bno, Model model) {
    //     BoardDto boardDto = boardService.getPost(bno);        
    //     boardService.updateView(bno); // views ++        
    //     model.addAttribute("boardDto", boardDto);         
    //     return "board-read"; }
    
}

package com.ktds.kart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ktds.kart.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
    List<Board> findByTitleContaining(String keyword);   
}

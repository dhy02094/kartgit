package com.ktds.kart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ktds.kart.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
    List<Board> findByTitleContaining(String keyword);

    // Optional<Board> findByNickname(String nickname);

    // // 중복인 경우 true, 중복되지 않은 경우 false 리턴
    // boolean existByNickname(String nickname);

//     // 조회수
    @Modifying
    @Query("update Board b set b.view = b.view + 1 where b.bno = :bno")
    int updateView(@Param("bno") Long bno);
}


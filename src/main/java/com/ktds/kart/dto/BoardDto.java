package com.ktds.kart.dto;

import java.time.LocalDateTime;

import com.ktds.kart.entity.Board;
import com.ktds.kart.entity.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long bno;
    private String title;
    private String content;
    private int view;
    private Long price;
    private LocalDateTime reg_date;
    private LocalDateTime mod_date;
    private Member nickname;
    
public Board toEntity() {
    Board build = Board.builder()
                .bno(bno)
                .title(title)
                .content(content)
                .view(view)
                .price(price)
                .nickname(nickname)
                .build();
    return build;
}

@Builder
public BoardDto(Long bno, String title, String content, int view, Long price, Member nickname, LocalDateTime reg_date, LocalDateTime mod_date) {
    this.bno = bno;
    this.title = title;
    this.content = content;
    this.view = view;
    this.price = price;
    this.nickname = nickname;
    this.reg_date = reg_date;
    this.mod_date = mod_date;
}
}


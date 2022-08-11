package com.ktds.kart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString(exclude = {"nickname","cid"})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_board")
@Entity
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(nullable = false,length = 100)
    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nickname")
    private Member nickname;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cid")
    private Category cid;

    @ColumnDefault("0")
    private Long view;

    @Column(columnDefinition ="boolean default false")
    private boolean complete;

    private Long price;


    @Builder
    public Board(Long bno, String title, String content, Long price) {
        this.bno = bno;
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public void update(Long bno, String title, String content, Long price) {
        this.bno = bno;
        this.title = title;
        this.content = content;
        this.price = price;
    }



    




    


    
}
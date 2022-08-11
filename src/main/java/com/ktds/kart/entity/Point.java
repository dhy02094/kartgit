package com.ktds.kart.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = "e_num")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_point")
@Entity
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="e_num")
    private Member e_num;

    @Column(nullable = false)
    private Long amount;
    
}

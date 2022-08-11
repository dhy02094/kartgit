package com.ktds.kart.entity;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = {"bno","cno"})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_file")
@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    private String img_path;
    private String img_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bno")
    private Board bno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cno")
    private Comment cno;
    
}

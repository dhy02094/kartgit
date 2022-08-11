package com.ktds.kart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ktds.kart.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {
    
}

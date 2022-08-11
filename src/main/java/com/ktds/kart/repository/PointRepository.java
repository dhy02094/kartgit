package com.ktds.kart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ktds.kart.entity.Point;

public interface PointRepository extends JpaRepository<Point,Long> {
    
}

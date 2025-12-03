package com.solvd.navigator.repository;

import com.solvd.navigator.domain.Point;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PointRepository {
    void savePoints(@Param("list") List<Point> points);

    List<Point> findAll();
}
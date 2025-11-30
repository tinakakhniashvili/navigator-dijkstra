package com.navigator.repository;

import com.navigator.domain.Point;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface PointRepository {
    void savePoints(@Param("list") List<Point> points);
    List<Point> findAll();
}
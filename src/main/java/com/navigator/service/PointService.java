package com.navigator.service;

import com.navigator.domain.Point;

import java.util.List;

public interface PointService {

    void initializePointsIfEmpty(int count);

    List<Point> getAllPoints();
}

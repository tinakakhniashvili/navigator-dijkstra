package com.solvd.navigator.service;

import com.solvd.navigator.domain.Point;

import java.util.List;

public interface PointService {

    List<Point> generateRandomPoints(Integer n);

}

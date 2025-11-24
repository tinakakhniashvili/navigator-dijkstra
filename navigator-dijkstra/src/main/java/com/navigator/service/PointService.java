package com.navigator.service;

import com.navigator.domain.Point;
import java.util.List;

public interface PointService {
    List<Point> generateRandomPoints(int n);
}

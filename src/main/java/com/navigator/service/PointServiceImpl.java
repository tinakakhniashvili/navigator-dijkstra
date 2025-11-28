package com.navigator.service;

import com.navigator.domain.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PointServiceImpl implements PointService {

    private static final double MAX_COORD = 100.0;

    @Override
    public List<Point> generateRandomPoints(Integer n) {
        List<Point> points = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            long id = i + 1L;
            double x = random.nextDouble() * MAX_COORD;
            double y = random.nextDouble() * MAX_COORD;
            points.add(new Point(id, x, y));
        }

        return points;
    }
}

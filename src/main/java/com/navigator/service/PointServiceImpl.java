package com.navigator.service;

import com.navigator.domain.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PointServiceImpl implements PointService {

    @Override
    public List<Point> generateRandomPoints(Integer n) {
        List<Point> points = new ArrayList<>();
        Random random = new Random();

        // generate random 100 points for x and y
        for (int i = 0; i < 100; i++) {
            Long id = (long) (i + 1);
            Double x = random.nextDouble() * 100;
            Double y = random.nextDouble() * 100;

            Point point = new Point(id, x, y);
            points.add(point);
        }

        return points;
    }
}

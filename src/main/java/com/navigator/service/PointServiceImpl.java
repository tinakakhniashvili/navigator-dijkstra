package com.navigator.service;

import com.navigator.domain.Point;
import com.navigator.repository.PointRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PointServiceImpl implements PointService {

    private static final double MAX_COORD = 100.0;
    private final PointRepository pointRepository;

    public PointServiceImpl(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @Override
    public void initializePointsIfEmpty(int count) {
        List<Point> existing = pointRepository.findAll();
        if (!existing.isEmpty()) {
            return;
        }
        List<Point> points = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            double x = random.nextDouble() * MAX_COORD;
            double y = random.nextDouble() * MAX_COORD;
            points.add(new Point(null, x, y));
        }
        pointRepository.savePoints(points);
    }

    @Override
    public List<Point> getAllPoints() {
        return pointRepository.findAll();
    }
}

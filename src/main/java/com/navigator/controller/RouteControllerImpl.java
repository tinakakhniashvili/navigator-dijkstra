package com.navigator.controller;

import com.navigator.domain.Point;
import com.navigator.domain.Route;
import com.navigator.service.PointService;
import com.navigator.service.RouteService;

import java.util.List;

public class RouteControllerImpl implements RouteController {

    private final PointService pointService;
    private final RouteService routeService;

    public RouteControllerImpl(PointService pointService, RouteService routeService) {
        this.pointService = pointService;
        this.routeService = routeService;
    }

    @Override
    public List<Route> calculateRandomRoutes(int numberOfPoints) {
        List<Point> randomPoints = pointService.generateRandomPoints(numberOfPoints);
        return routeService.computeRoutes(randomPoints);
    }
}

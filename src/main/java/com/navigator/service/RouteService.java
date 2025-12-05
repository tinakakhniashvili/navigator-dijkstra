package com.navigator.service;

import com.navigator.domain.Point;
import com.navigator.domain.Route;

import java.util.List;

public interface RouteService {

    List<Route> findShortestAndAlternativeRoutes(List<Point> points, long startId, long endId);
}

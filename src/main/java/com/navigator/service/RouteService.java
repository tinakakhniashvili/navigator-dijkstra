package com.navigator.service;

import com.navigator.domain.Point;
import com.navigator.domain.Route;

import java.util.List;

public interface RouteService {

    // Takes points and returns one or more routes.
    // The first one should be the fastest.

    List<Route> computeRoutes(List<Point> points);
}

package com.navigator.controller;

import com.navigator.domain.Route;

import java.util.List;

public interface RouteController {

    List<Route> findShortestAndAlternativeRoutes(long startId, long endId);
}

package com.solvd.navigator.controller;

import com.solvd.navigator.domain.Route;

import java.util.List;

public interface RouteController {

    List<Route> calculateRandomRoutes(int numberOfPoints);

}

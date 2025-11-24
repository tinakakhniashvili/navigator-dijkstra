package com.navigator.controller;

import com.navigator.domain.Route;
import java.util.List;

public interface RouteController {
    List<Route> calculateRandomRoutes(int numberOfPoints);
}

package com.navigator;

import com.navigator.controller.RouteController;
import com.navigator.controller.RouteControllerImpl;
import com.navigator.domain.Route;
import com.navigator.service.PointService;
import com.navigator.service.PointServiceImpl;
import com.navigator.service.RouteService;
import com.navigator.service.RouteServiceImpl;
import com.navigator.view.ConsoleView;

import java.util.List;

public class App {
    public static void main(String[] args) {
        PointService pointService = new PointServiceImpl();
        RouteService routeService = new RouteServiceImpl();
        RouteController controller = new RouteControllerImpl(pointService, routeService);
        ConsoleView view = new ConsoleView();

        int numberOfPoints = 100;
        List<Route> routes = controller.calculateRandomRoutes(numberOfPoints);

        view.printRoutes(routes);
    }
}

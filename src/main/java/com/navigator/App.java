package com.navigator;

import com.navigator.controller.RouteController;
import com.navigator.controller.RouteControllerImpl;
import com.navigator.domain.Route;
import com.navigator.repository.PointRepository;
import com.navigator.repository.RoutePointRepository;
import com.navigator.repository.RouteRepository;
import com.navigator.repository.impl.PointRepositoryImpl;
import com.navigator.repository.impl.RoutePointRepositoryImpl;
import com.navigator.repository.impl.RouteRepositoryImpl;
import com.navigator.service.PointService;
import com.navigator.service.PointServiceImpl;
import com.navigator.service.RouteService;
import com.navigator.service.RouteServiceImpl;
import com.navigator.view.ConsoleView;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // initialize repositories
        PointRepository pointRepository = new PointRepositoryImpl();
        RouteRepository routeRepository = new RouteRepositoryImpl();
        RoutePointRepository routePointRepository = new RoutePointRepositoryImpl();

        // initialize services with repositories
        PointService pointService = new PointServiceImpl(pointRepository);
        RouteService routeService = new RouteServiceImpl(routeRepository, routePointRepository);

        // initialize controller
        RouteController controller = new RouteControllerImpl(pointService, routeService);
        ConsoleView view = new ConsoleView();

        int numberOfPoints = 100;
        List<Route> routes = controller.calculateRandomRoutes(numberOfPoints);

        view.printRoutes(routes);
    }
}
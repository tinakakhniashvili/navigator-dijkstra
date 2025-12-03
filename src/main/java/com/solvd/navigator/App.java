package com.solvd.navigator;

import com.solvd.navigator.controller.RouteController;
import com.solvd.navigator.controller.RouteControllerImpl;
import com.solvd.navigator.domain.Route;
import com.solvd.navigator.repository.PointRepository;
import com.solvd.navigator.repository.RoutePointRepository;
import com.solvd.navigator.repository.RouteRepository;
import com.solvd.navigator.repository.impl.PointRepositoryImpl;
import com.solvd.navigator.repository.impl.RoutePointRepositoryImpl;
import com.solvd.navigator.repository.impl.RouteRepositoryImpl;
import com.solvd.navigator.service.PointService;
import com.solvd.navigator.service.PointServiceImpl;
import com.solvd.navigator.service.RouteService;
import com.solvd.navigator.service.RouteServiceImpl;
import com.solvd.navigator.view.ConsoleView;

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
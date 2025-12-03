package com.solvd.navigator;

import com.solvd.navigator.controller.RouteController;
import com.solvd.navigator.controller.RouteControllerImpl;
import com.solvd.navigator.domain.Point;
import com.solvd.navigator.domain.Route;
import com.solvd.navigator.repository.PointRepository;
import com.solvd.navigator.repository.RoutePointRepository;
import com.solvd.navigator.repository.RouteRepository;
import com.solvd.navigator.repository.impl.PointRepositoryImpl;
import com.solvd.navigator.repository.impl.RoutePointRepositoryImpl;
import com.solvd.navigator.repository.impl.RouteRepositoryImpl;
import com.solvd.navigator.service.PointService;
import com.solvd.navigator.service.impl.PointServiceImpl;
import com.solvd.navigator.service.RouteService;
import com.solvd.navigator.service.impl.RouteServiceImpl;

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

        int numberOfPoints = 100;
        List<Route> routes = controller.calculateRandomRoutes(numberOfPoints);

        printRoutes(routes);
    }

    private static void printRoutes(List<Route> routes) {
        int idx = 1;
        for (Route route : routes) {
            System.out.println("===== Route #" + idx + " =====");
            for (Point p : route.getPoints()) {
                System.out.printf("Point %d: (%.2f, %.2f)%n",
                        p.getId(), p.getX(), p.getY());
            }
            System.out.printf("Total time: %.2f seconds%n", route.getTotalTime());
            System.out.println();
            idx++;
        }
    }
}

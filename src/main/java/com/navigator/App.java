package com.navigator;

import com.navigator.controller.RouteController;
import com.navigator.controller.RouteControllerImpl;
import com.navigator.domain.Point;
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

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        PointRepository pointRepository = new PointRepositoryImpl();
        RouteRepository routeRepository = new RouteRepositoryImpl();
        RoutePointRepository routePointRepository = new RoutePointRepositoryImpl();

        PointService pointService = new PointServiceImpl(pointRepository);
        RouteService routeService = new RouteServiceImpl(routeRepository, routePointRepository);

        RouteController controller = new RouteControllerImpl(pointService, routeService);

        int numberOfPoints = 100;
        pointService.initializePointsIfEmpty(numberOfPoints);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter start point id: ");
        long startId = scanner.nextLong();
        System.out.print("Enter end point id: ");
        long endId = scanner.nextLong();

        List<Route> routes = controller.findShortestAndAlternativeRoutes(startId, endId);
        printRoutes(routes);
    }

    private static void printRoutes(List<Route> routes) {
        int idx = 1;
        for (Route route : routes) {
            System.out.println("===== Route #" + idx + " =====");
            for (Point p : route.getPoints()) {
                System.out.printf("Point %d: (%.2f, %.2f)%n", p.getId(), p.getX(), p.getY());
            }
            System.out.printf("Total time: %.4f seconds%n", route.getTotalTime());
            System.out.println();
            idx++;
        }
    }
}

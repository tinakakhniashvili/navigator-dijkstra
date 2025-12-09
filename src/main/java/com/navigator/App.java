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

        long minId = 1;
        long maxId = numberOfPoints;

        System.out.printf("Available point ids are from %d to %d%n", minId, maxId);

        Scanner scanner = new Scanner(System.in);
        long startId = readValidId(scanner, "Enter start point id: ", minId, maxId);
        long endId = readValidId(scanner, "Enter end point id: ", minId, maxId);

        List<Route> routes = controller.findShortestAndAlternativeRoutes(startId, endId);
        printRoutes(routes);
    }

    private static long readValidId(Scanner scanner, String prompt, long minId, long maxId) {
        while (true) {
            System.out.print(prompt);
            long id = scanner.nextLong();
            if (id >= minId && id <= maxId) {
                return id;
            }
            System.out.printf("Invalid id. Please enter a value between %d and %d.%n", minId, maxId);
        }
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

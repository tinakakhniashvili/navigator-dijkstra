package com.navigator.view;

import com.navigator.domain.Point;
import com.navigator.domain.Route;

import java.util.List;

public class ConsoleView {

    public void printRoutes(List<Route> routes) {
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

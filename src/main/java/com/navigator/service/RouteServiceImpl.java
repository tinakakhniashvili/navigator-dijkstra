package com.navigator.service;

import com.navigator.domain.Point;
import com.navigator.domain.Route;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RouteServiceImpl implements RouteService {

    private static final double SPEED = 1.0;

    @Override
    public List<Route> computeRoutes(List<Point> points) {
        Route route1 = naiveRoute(points);
        Route route2 = nearestNeighborRoute(points);

        List<Route> routes = new ArrayList<>();

        if (route1.getTotalTime() <= route2.getTotalTime()) {
            routes.add(route1);
            routes.add(route2);
        } else {
            routes.add(route2);
            routes.add(route1);
        }
        return routes;
    }

    private Route naiveRoute(List<Point> points) {
        Route route = new Route();
        double time = 0.0;

        Point prev = null;
        for (Point p : points) {
            if (prev != null) {
                time += prev.distanceTo(p) / SPEED;
            }
            route.addPoint(p);
            prev = p;
        }
        route.setTotalTime(time);
        return route;
    }

    private Route nearestNeighborRoute(List<Point> points) {
        if (points.isEmpty()) {
            return new Route();
        }

        List<Point> remaining = new ArrayList<>(points);
        Set<Long> visited = new HashSet<>();

        Route route = new Route();
        double time = 0.0;

        Point current = remaining.get(0);
        route.addPoint(current);
        visited.add(current.getId());

        while (visited.size() < remaining.size()) {
            Point next = findNearest(current, remaining, visited);
            time += current.distanceTo(next) / SPEED;
            route.addPoint(next);
            visited.add(next.getId());
            current = next;
        }

        route.setTotalTime(time);
        return route;
    }

    private Point findNearest(Point from, List<Point> candidates, Set<Long> visited) {
        Point best = null;
        double bestDist = Double.MAX_VALUE;

        for (Point p : candidates) {
            if (visited.contains(p.getId())) continue;
            double d = from.distanceTo(p);
            if (d < bestDist) {
                bestDist = d;
                best = p;
            }
        }
        return best;
    }
}

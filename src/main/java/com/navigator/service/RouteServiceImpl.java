package com.navigator.service;

import com.navigator.domain.Point;
import com.navigator.domain.Route;
import com.navigator.repository.RoutePointRepository;
import com.navigator.repository.RouteRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class RouteServiceImpl implements RouteService {

    private static final double SPEED = 1.0;
    private final RouteRepository routeRepository;
    private final RoutePointRepository routePointRepository;

    public RouteServiceImpl(RouteRepository routeRepository, RoutePointRepository routePointRepository) {
        this.routeRepository = routeRepository;
        this.routePointRepository = routePointRepository;
    }

    @Override
    public List<Route> findShortestAndAlternativeRoutes(List<Point> points, long startId, long endId) {
        Route shortest = findShortestRoute(points, startId, endId, Collections.emptySet());
        if (shortest == null) {
            return Collections.emptyList();
        }
        Route alternative = findAlternativeRoute(points, startId, endId, shortest);
        saveRouteWithPoints(shortest);
        if (alternative == null) {
            return List.of(shortest);
        }
        saveRouteWithPoints(alternative);
        return List.of(shortest, alternative);
    }

    private void saveRouteWithPoints(Route route) {
        routeRepository.saveRoute(route);
        List<Point> routePoints = route.getPoints();
        for (int i = 0; i < routePoints.size(); i++) {
            routePointRepository.saveRoutePoint(route.getId(), routePoints.get(i).getId(), i);
        }
    }

    private Route findAlternativeRoute(List<Point> points, long startId, long endId, Route shortest) {
        List<Point> path = shortest.getPoints();
        if (path.size() < 2) {
            return null;
        }
        Route best = null;
        for (int i = 0; i < path.size() - 1; i++) {
            long fromId = path.get(i).getId();
            long toId = path.get(i + 1).getId();
            Set<String> blockedEdges = new HashSet<>();
            blockedEdges.add(edgeKey(fromId, toId));
            blockedEdges.add(edgeKey(toId, fromId));
            Route candidate = findShortestRoute(points, startId, endId, blockedEdges);
            if (candidate == null) {
                continue;
            }
            if (best == null || candidate.getTotalTime() < best.getTotalTime()) {
                best = candidate;
            }
        }
        return best;
    }

    private String edgeKey(long fromId, long toId) {
        return fromId + "->" + toId;
    }

    private Route findShortestRoute(List<Point> points, long startId, long endId, Set<String> blockedEdges) {
        Map<Long, Point> byId = new HashMap<>();
        for (Point p : points) {
            byId.put(p.getId(), p);
        }
        Point start = byId.get(startId);
        Point end = byId.get(endId);
        if (start == null || end == null) {
            throw new IllegalArgumentException("Invalid start or end id");
        }
        Map<Long, Double> dist = new HashMap<>();
        Map<Long, Long> prev = new HashMap<>();
        Set<Long> visited = new HashSet<>();
        for (Point p : points) {
            dist.put(p.getId(), Double.POSITIVE_INFINITY);
        }
        dist.put(startId, 0.0);

        class Node {
            long id;
            double d;
            Node(long id, double d) {
                this.id = id;
                this.d = d;
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.d));
        pq.add(new Node(startId, 0.0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            long u = node.id;
            if (!visited.add(u)) {
                continue;
            }
            if (u == endId) {
                break;
            }
            Point pu = byId.get(u);
            for (Point pv : points) {
                long v = pv.getId();
                if (v == u || visited.contains(v)) {
                    continue;
                }
                if (blockedEdges.contains(edgeKey(u, v))) {
                    continue;
                }
                double w = pu.distanceTo(pv) / SPEED;
                double alt = dist.get(u) + w;
                if (alt < dist.get(v)) {
                    dist.put(v, alt);
                    prev.put(v, u);
                    pq.add(new Node(v, alt));
                }
            }
        }
        if (!dist.containsKey(endId) || dist.get(endId) == Double.POSITIVE_INFINITY) {
            return null;
        }
        List<Point> path = new ArrayList<>();
        long cur = endId;
        path.add(byId.get(cur));
        while (cur != startId) {
            Long pId = prev.get(cur);
            if (pId == null) {
                return null;
            }
            cur = pId;
            path.add(byId.get(cur));
        }
        Collections.reverse(path);
        Route route = new Route();
        for (Point p : path) {
            route.addPoint(p);
        }
        route.setTotalTime(dist.get(endId));
        return route;
    }
}

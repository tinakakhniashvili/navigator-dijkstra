package com.solvd.navigator.domain;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private Long id;
    private List<Point> points;
    private Double totalTime;

    public Route() {
        this.points = new ArrayList<>();
        this.totalTime = 0.0;
    }

    public Route(List<Point> points, Double totalTime) {
        this.points = points;
        this.totalTime = totalTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Point> getPoints() {
        return points;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    // helper method to add a point to the route
    public void addPoint(Point point) {
        this.points.add(point);
    }

    @Override
    public String toString() {
        return "Route{" +
                "numberOfPoints=" + points.size() +
                ", totalTime=" + String.format("%.2f", totalTime) +
                " seconds}";
    }
}
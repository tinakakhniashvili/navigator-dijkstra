package com.solvd.navigator.domain;

public class Point {
    private Long id;
    private Double x;
    private Double y;

    public Point(Long id, Double x, Double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double distanceTo(Point other) {
        Double dx = this.x - other.x;
        Double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

}

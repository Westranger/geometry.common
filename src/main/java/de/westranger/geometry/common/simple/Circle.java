package de.westranger.geometry.common.simple;

public class Circle extends Geometry {
    protected final Point2D center;
    protected final double radius;

    public Circle(final Point2D center, final double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point2D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }
}

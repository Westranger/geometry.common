package de.westranger.geometry.common.simple;

import de.westranger.geometry.common.math.Vector2D;

public final class Point2D {
    private final double x;
    private final double y;

    public Point2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D pointAt(final double angle, final double distance) {
        return new Point2D(this.x + Math.cos(angle) * distance, this.y + Math.sin(angle) * distance);
    }

    public Point2D pointAt(final Vector2D vec) {
        return new Point2D(this.x + vec.getX(), this.y + vec.getY());
    }

    public Vector2D diff(final Point2D point) {
        return new Vector2D(point.getX() - this.x, point.getY() - this.y);
    }

    public double distance(final Point2D point) {
        return this.diff(point).norm();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

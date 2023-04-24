package de.westranger.geometry.common.simple;

import de.westranger.geometry.common.math.Vector2D;

import java.util.Objects;

public final class Point2D implements Comparable<Point2D> {
    private final double x;
    private final double y;

    public Point2D(final double x, final double y) {
        super();
        if (Double.isNaN(x) || Double.isNaN(y)) {
            throw new ArithmeticException("Point2D components mut not be Nan x= " + x + " y=" + y);
        }
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

    public Vector2D toVector2D() {
        return new Vector2D(x, y);
    }

    @Override
    public String toString() {
        return "Punkt(" + this.x + "," + this.y + ")";
    }

    @Override
    public int compareTo(final Point2D o) {
        return (int) (((this.getX() - o.getX()) + (this.getY() - o.getY())) * 1e5);
    }

    public static double angleBetween(final Point2D p1, final Point2D p2, final Point2D p3) {
        final Vector2D v1 = p2.diff(p1);
        final Vector2D v2 = p2.diff(p3);
        return v1.angleBetween(v2);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Point2D point2D = (Point2D) o;
        return Double.compare(point2D.x, x) == 0 && Double.compare(point2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

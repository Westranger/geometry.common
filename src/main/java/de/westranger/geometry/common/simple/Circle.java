package de.westranger.geometry.common.simple;

import de.westranger.geometry.common.math.Vector2D;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Circle extends Geometry {
    protected final Point2D center;
    protected final double radius;

    public Circle(final Point2D center, final double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Optional<List<Point2D>> intersection(final Circle circle) {
        // https://stackoverflow.com/questions/3349125/circle-circle-intersection-points#:~:text=Intersection%20of%20two%20circles&text=The%20aim%20is%20to%20find,solutions%2C%20the%20circles%20are%20separate.

        final double dist = this.center.distance(circle.getCenter());

        if (dist > this.radius + circle.getRadius() || dist < Math.abs(this.radius - circle.getRadius()) || dist <= 1e-10 && this.radius - circle.getRadius() <= 1e-10) {
            return Optional.empty();
        }

        final double a = (this.radius * this.radius - circle.getRadius() * circle.getRadius() + dist * dist) / (2.0 * dist);
        final double h = Math.sqrt(this.radius * this.radius - a * a);
        final Vector2D p2 = toVector(this.center).add(toVector(circle.getCenter()).subtract(toVector(this.center)).multiply(a).multiply(1.0 / dist));
        final double x31 = p2.getX() + h * (circle.getCenter().getY() - this.center.getY()) / dist;
        final double y31 = p2.getY() - h * (circle.getCenter().getX() - this.center.getX()) / dist;
        final double x32 = p2.getX() - h * (circle.getCenter().getY() - this.center.getY()) / dist;
        final double y32 = p2.getY() + h * (circle.getCenter().getX() - this.center.getX()) / dist;
        final Point2D resA = new Point2D(x31, y31);
        final Point2D resB = new Point2D(x32, y32);

        final List<Point2D> result = new LinkedList<>();
        if (resA.distance(resB) > 1e-10) {
            result.add(resB);
        }

        result.add(resA);
        return Optional.of(result);
    }

    public Point2D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    private Vector2D toVector(final Point2D point) {
        return new Vector2D(point.getX(), point.getY());
    }
}

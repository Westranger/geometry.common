package de.westranger.geometry.common.simple;

import de.westranger.geometry.common.math.Vector2D;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public final class Line extends Geometry {
    private final Vector2D positionVector;
    private final Vector2D directionVector;

    public Line(final Vector2D positionVector, final Vector2D directionVector) {
        this.positionVector = positionVector;
        this.directionVector = directionVector;
    }

    public Line(final Point2D pointA, final Point2D pointB) {
        this.positionVector = new Vector2D(pointA.getX(), pointA.getY());
        this.directionVector = pointA.diff(pointB);
    }

    // https://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect
    // https://github.com/pgkelley4/line-segments-intersect/blob/master/js/line-segments-intersect.js
    public OptionalDouble intersection(final Line line) {
        final Vector2D r = this.directionVector;
        final Vector2D s = line.directionVector;
        final double numerator = line.getPositionVector().subtract(this.positionVector).cross(r);
        final double denominator = r.cross(s);

        if (Math.abs(numerator) < 1e-10 && Math.abs(denominator) < 1e-10) {
            // colinear see link for detail whenever we want to return information about colinearity
            return OptionalDouble.of(Double.POSITIVE_INFINITY);
        } else if (Math.abs(denominator) < 1e-10) {
            // parallel
            return OptionalDouble.empty(); // Lines are parallel.
        }

        //final double u = numerator / denominator;
        final double t = line.getPositionVector().subtract(this.positionVector).cross(s) / denominator;
        return OptionalDouble.of(t);
    }

    public Point2D getClosestPointOnLine(final Point2D point) {
        throw new RuntimeException("Not Yet Implemented");
    }

    public Optional<List<Point2D>> intersection(final Circle circle) {
        throw new RuntimeException("Not Yet Implemented");
    }

    public Optional<List<Point2D>> intersection(final ArcCircle arc) {
        throw new RuntimeException("Not Yet Implemented");
    }

    public Optional<List<Point2D>> intersection(final Ellipse elipse) {
        throw new RuntimeException("Not Yet Implemented");
    }

    public Optional<List<Point2D>> intersection(final ArcEllipse arc) {
        throw new RuntimeException("Not Yet Implemented");
    }

    public Vector2D getPositionVector() {
        return positionVector;
    }

    public Vector2D getDirectionVector() {
        return directionVector;
    }
}

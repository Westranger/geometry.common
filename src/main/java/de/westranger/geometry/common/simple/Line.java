package de.westranger.geometry.common.simple;

import de.westranger.geometry.common.math.Vector2D;

import java.util.LinkedList;
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
        final double numerator = line.getPositionVector().subtract(this.positionVector).cross(this.directionVector);
        final double denominator = this.directionVector.cross(line.directionVector);

        if (Math.abs(numerator) < 1e-10 && Math.abs(denominator) < 1e-10) {
            // colinear see link for detail whenever we want to return information about colinearity
            return OptionalDouble.of(Double.POSITIVE_INFINITY);
        } else if (Math.abs(denominator) < 1e-10) {
            // parallel
            return OptionalDouble.empty(); // Lines are parallel.
        }

        //final double u = numerator / denominator;
        final double t = line.getPositionVector().subtract(this.positionVector).cross(line.directionVector) / denominator;
        return OptionalDouble.of(t);
    }

    public Point2D getClosestPointOnLine(final Point2D point) {
        final Line line = new Line(new Vector2D(point.getX(), point.getY()), this.directionVector.turn90DegreeClockwise());
        final OptionalDouble intersection = this.intersection(line);
        final Vector2D result = this.positionVector.add(this.directionVector.lerp(intersection.getAsDouble()));
        return new Point2D(result.getX(), result.getY());
    }

    public Optional<List<Point2D>> intersection(final Circle circle) {
        final Point2D intersection = this.getClosestPointOnLine(circle.getCenter());
        final double dist = intersection.distance(circle.getCenter());
        if (Math.abs(dist - circle.getRadius()) < 1e-10) {
            final List<Point2D> result = new LinkedList<>();
            result.add(intersection);
            return Optional.of(result);
        } else if (dist < circle.getRadius()) {
            // we have two points
            final double adjacentLeg = Math.sqrt(circle.getRadius() * circle.getRadius() - dist * dist);
            final Point2D pt1 = intersection.pointAt(this.directionVector.angle(), adjacentLeg);
            final Point2D pt2 = intersection.pointAt(this.directionVector.angle() + Math.PI, adjacentLeg);

            final List<Point2D> result = new LinkedList<>();
            result.add(pt1);
            result.add(pt2);
            return Optional.of(result);
        }
        return Optional.empty();
    }

    public Optional<List<Point2D>> intersection(final ArcCircle arc) {
        final Optional<List<Point2D>> intersection = this.intersection((Circle) arc);

        if (intersection.isEmpty()) {
            return intersection;
        }
        final List<Point2D> result = new LinkedList<>();
        final Vector2D directionStart = arc.getCenter().diff(arc.getArcStart());
        for (Point2D pt : intersection.get()) {
            final Vector2D directionPt = arc.getCenter().diff(pt);
            final double angleBetween = directionStart.angleBetweenSinged(directionPt);
            if (arc.getArcLength() > angleBetween && angleBetween >= 0.0 || arc.getArcLength() < angleBetween && angleBetween < 0.0) {
                result.add(pt);
            }
        }

        if (result.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(result);
    }

    public Vector2D getPositionVector() {
        return positionVector;
    }

    public Vector2D getDirectionVector() {
        return directionVector;
    }
}

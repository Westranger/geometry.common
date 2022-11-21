package de.westranger.geometry.common.simple;

import de.westranger.geometry.common.math.Vector2D;

import java.util.OptionalDouble;

public final class Segment extends Line {
    private final Point2D start;
    private final Point2D end;

    public Segment(final Point2D start, final Point2D end) {
        super(start, end);
        this.start = start;
        this.end = end;
    }

    public Point2D getClosestPointOnLine(final Point2D point) {
        final Line line = new Line(new Vector2D(point.getX(), point.getY()), this.directionVector.turn90DegreeClockwise());
        final OptionalDouble intersection = this.computeIntersection(line);
        if (intersection.getAsDouble() < 0.0) {
            return this.start;
        } else if (intersection.getAsDouble() > 1.0) {
            return this.end;
        }

        final Vector2D result = this.positionVector.add(this.directionVector.lerp(intersection.getAsDouble()));
        return new Point2D(result.getX(), result.getY());
    }

    public Point2D getStart() {
        return start;
    }

    public Point2D getEnd() {
        return end;
    }

    @Override
    public BoundingBox getBoundingBox() {
        final double xMin = Math.min(this.start.getX(), this.end.getX());
        final double xMax = Math.max(this.start.getX(), this.end.getX());
        final double yMin = Math.min(this.start.getY(), this.end.getY());
        final double yMax = Math.max(this.start.getY(), this.end.getY());

        return new BoundingBox(new Point2D(xMin, yMin), new Point2D(xMax, yMax));
    }
}

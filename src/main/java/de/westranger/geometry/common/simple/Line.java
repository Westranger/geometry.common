package de.westranger.geometry.common.simple;

import de.westranger.geometry.common.Side;
import de.westranger.geometry.common.math.Vector2D;

import java.util.OptionalDouble;

public class Line extends Geometry {
    protected final Vector2D positionVector;
    protected final Vector2D directionVector;

    /**
     * @param point
     * @return
     * @see <a href="https://math.stackexchange.com/questions/274712/calculate-on-which-side-of-a-straight-line-is-a-given-point-located">Calculate on which side of a straight line is a given point located?</a>
     */
    // TODO auch für pose implementieren ... oder
    public Side computeSide(final Point2D point) {
        final double d = point.toVector2D().subtract(this.positionVector).cross(this.directionVector);
        if (Math.abs(d) < 1e-10) {
            return Side.Middle;
        } else if (d > 0.0) {
            return Side.Right;
        }
        return Side.Left;
    }

    public Line(final Vector2D positionVector, final Vector2D directionVector) {
        if (directionVector.norm() < 1e-10) {
            throw new IllegalArgumentException("the direction vector norm is smaller than 1e-10 which is to small");
        } else if (Double.isInfinite(positionVector.getX()) || Double.isInfinite(positionVector.getY())) {
            throw new IllegalArgumentException("components of the direction vector must not be infinite x=" + positionVector.getX() + " y=" + positionVector.getY());
        } else if (Double.isInfinite(directionVector.getX()) && Double.isInfinite(directionVector.getY())) {
            throw new IllegalArgumentException("components are both infinite");
        }

        this.positionVector = positionVector;
        this.directionVector = directionVector;
    }

    public Line(final Point2D pointA, final Point2D pointB) {
        this(new Vector2D(pointA.getX(), pointA.getY()), pointA.diff(pointB));
    }

    /**
     * @param line
     * @return
     * @see
     * @see <a href="https://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect">http://google.com</a>
     * @see <a href="https://github.com/pgkelley4/line-segments-intersect/blob/master/js/line-segments-intersect.js">http://google.com</a>
     */
    // TODO Testcase für den Fall wenn einen Komponente des direction vector inf ist
    public OptionalDouble computeIntersection(final Line line) {
        final double numerator = line.getPositionVector().subtract(this.positionVector).cross(this.directionVector);
        final double denominator = this.directionVector.cross(line.directionVector);

        if (Math.abs(numerator) < 1e-10 && Math.abs(denominator) < 1e-10) {
            // collinear see link for detail whenever we want to return information about collinearity
            return OptionalDouble.of(Double.POSITIVE_INFINITY);
        } else if (Math.abs(denominator) < 1e-10) {
            // parallel
            return OptionalDouble.empty(); // Lines are parallel.
        }

        //final double u = numerator / denominator;
        final double t = line.getPositionVector().subtract(this.positionVector).cross(line.directionVector) / denominator;
        return OptionalDouble.of(t);
    }

    public Vector2D getPositionVector() {
        return positionVector;
    }

    public Vector2D getDirectionVector() {
        return directionVector;
    }

    public Point2D getClosestPointOnLine(final Point2D point) {
        final Line line = new Line(new Vector2D(point.getX(), point.getY()), this.directionVector.turn90DegreeClockwise());
        final OptionalDouble intersection = this.computeIntersection(line);
        final Vector2D result = this.positionVector.add(this.directionVector.lerp(intersection.getAsDouble()));
        return new Point2D(result.getX(), result.getY());
    }

    @Override
    public BoundingBox getBoundingBox() {
        return new BoundingBox(new Point2D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY), new Point2D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY));
    }
}

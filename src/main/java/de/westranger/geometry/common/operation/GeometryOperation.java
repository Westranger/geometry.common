package de.westranger.geometry.common.operation;

import de.westranger.geometry.common.math.Vector2D;
import de.westranger.geometry.common.operation.result.GeometryResult;
import de.westranger.geometry.common.operation.result.PointResult;
import de.westranger.geometry.common.simple.*;

import java.util.*;

public final class GeometryOperation {
    public static GeometryResult<Line> intersection(final Line l1, final Line l2) {
        final OptionalDouble inter = l1.computeIntersection(l2);
        if (inter.isEmpty()) {
            return new GeometryResult<>();
        } else if (Double.isInfinite(inter.getAsDouble())) {
            return new GeometryResult<>(l1);
        } else {
            final Vector2D vec = l1.getPositionVector().add(l1.getDirectionVector().lerp(inter.getAsDouble()));
            final List<Point2D> lst = new ArrayList<>(1);
            lst.add(new Point2D(vec.getX(), vec.getY()));
            return new GeometryResult<>(Collections.unmodifiableList(lst));
        }
    }

    public static GeometryResult<Line> intersection(final Line l1, final Segment s1) {
        return null;
    }

    public static PointResult intersection(final Line l1, final Circle c1) {
        final Point2D intersection = l1.getClosestPointOnLine(c1.getCenter());
        final double dist = intersection.distance(c1.getCenter());
        if (Math.abs(dist - c1.getRadius()) < 1e-10) {
            final List<Point2D> result = new LinkedList<>();
            result.add(intersection);
            return new PointResult(Collections.unmodifiableList(result));
        } else if (dist < c1.getRadius()) {
            // we have two points
            final double adjacentLeg = Math.sqrt(c1.getRadius() * c1.getRadius() - dist * dist);
            final Point2D pt1 = intersection.pointAt(l1.getDirectionVector().angle(), adjacentLeg);
            final Point2D pt2 = intersection.pointAt(l1.getDirectionVector().angle() + Math.PI, adjacentLeg);

            final List<Point2D> result = new LinkedList<>();
            result.add(pt1);
            result.add(pt2);
            return new PointResult(Collections.unmodifiableList(result));
        }
        return new PointResult();
    }

    public static PointResult intersection(final Line l1, final ArcCircle ac1) {
        final PointResult intersection = GeometryOperation.intersection(l1, (Circle) ac1);

        if (intersection.getPoints().isEmpty()) {
            return new PointResult();
        }
        final List<Point2D> result = new LinkedList<>();
        final Vector2D directionStart = ac1.getCenter().diff(ac1.getArcStart());
        for (Point2D pt : intersection.getPoints().get()) {
            final Vector2D directionPt = ac1.getCenter().diff(pt);
            final double angleBetween = directionStart.angleBetweenSinged(directionPt);
            if (ac1.getArcLength() > angleBetween && angleBetween >= 0.0 || ac1.getArcLength() < angleBetween && angleBetween < 0.0) {
                result.add(pt);
            }
        }

        if (result.isEmpty()) {
            return new PointResult();
        }

        return new PointResult(Collections.unmodifiableList(result));
    }

    public static PointResult intersection(final Line l1, final Ellipse e1) {
        return null;
    }

    public static PointResult intersection(final Line l1, final ArcEllipse ae1) {
        return null;
    }

    public static GeometryResult<Segment> intersection(final Segment s1, final Line l1) {
        return null;
    }

    public static GeometryResult<Segment> intersection(final Segment s1, final Segment s2) {
        final OptionalDouble intersection = s1.computeIntersection(s2);

        if (intersection.isPresent()) {
            final double intersectionValue = intersection.getAsDouble();
            if (Double.isFinite(intersectionValue)) {
                // normal case
                if (intersectionValue >= 0.0 && intersectionValue <= 1.0) {
                    // we have a valid intersection on the segments
                    final Vector2D vec = s1.getPositionVector().add(s1.getDirectionVector().lerp(intersectionValue));
                    final List<Point2D> lst = new ArrayList<>(1);
                    lst.add(new Point2D(vec.getX(), vec.getY()));
                    return new GeometryResult<>(Collections.unmodifiableList(lst));
                } // else the intersection is not on the segments
            } else {
                // we need to return a segment or maybe not
                final Vector2D diffS1StartS2Start = s2.getStart().toVector2D().subtract(s1.getStart().toVector2D());
                final Vector2D diffS1StartS2End = s2.getEnd().toVector2D().subtract(s1.getStart().toVector2D());
                final Vector2D directionS1 = s1.getDirectionVector();
                final double tS1S2Start = Math.abs(directionS1.getX()) < 1e-10 ? diffS1StartS2Start.getY() / directionS1.getY() : diffS1StartS2Start.getX() / directionS1.getX();
                final double tS1S2End = Math.abs(directionS1.getX()) < 1e-10 ? diffS1StartS2End.getY() / directionS1.getY() : diffS1StartS2End.getX() / directionS1.getX();

                if (tS1S2Start >= 0.0 && tS1S2Start <= 1.0) {
                    if (tS1S2End >= 0.0 && tS1S2End <= 1.0) {
                        return new GeometryResult<>(new Segment(s2.getStart(), s2.getEnd()));
                    } else if (tS1S2End < 0.0) {
                        return new GeometryResult<>(new Segment(s2.getStart(), s1.getStart()));
                    } else if (tS1S2End > 1.0) {
                        return new GeometryResult<>(new Segment(s2.getStart(), s1.getEnd()));
                    }
                } else if (tS1S2End >= 0.0 && tS1S2End <= 1.0) {
                    if (tS1S2Start < 0.0) {
                        return new GeometryResult<>(new Segment(s2.getEnd(), s1.getStart()));
                    } else if (tS1S2Start > 1.0) {
                        return new GeometryResult<>(new Segment(s2.getEnd(), s1.getEnd()));
                    }
                }
            }
        }

        return new GeometryResult<>();
    }

    public static PointResult intersection(final Segment s1, final Circle c1) {
        return null;
    }

    public static PointResult intersection(final Segment s1, final ArcCircle ac1) {
        return null;
    }

    public static PointResult intersection(final Segment s1, final Ellipse e1) {
        return null;
    }

    public static PointResult intersection(final Segment s1, final ArcEllipse ae1) {
        return null;
    }

    public static GeometryResult<Circle> intersection(final Circle c1, final Circle c2) {
        // https://stackoverflow.com/questions/3349125/circle-circle-intersection-points#:~:text=Intersection%20of%20two%20circles&text=The%20aim%20is%20to%20find,solutions%2C%20the%20circles%20are%20separate.

        final double dist = c1.getCenter().distance(c2.getCenter());

        if (dist <= 1e-10 && c1.getRadius() - c2.getRadius() <= 1e-10) {
            return new GeometryResult(c1);
        } else if (dist > c1.getRadius() + c2.getRadius() || dist < Math.abs(c1.getRadius() - c2.getRadius())) {
            return new GeometryResult();
        }

        final double a = (c1.getRadius() * c1.getRadius() - c2.getRadius() * c2.getRadius() + dist * dist) / (2.0 * dist);
        final double h = Math.sqrt(c1.getRadius() * c1.getRadius() - a * a);
        final Vector2D p2 = c1.getCenter().toVector2D().add(c2.getCenter().toVector2D().subtract(c1.getCenter().toVector2D()).multiply(a).multiply(1.0 / dist));
        final double x31 = p2.getX() + h * (c2.getCenter().getY() - c1.getCenter().getY()) / dist;
        final double y31 = p2.getY() - h * (c2.getCenter().getX() - c1.getCenter().getX()) / dist;
        final double x32 = p2.getX() - h * (c2.getCenter().getY() - c1.getCenter().getY()) / dist;
        final double y32 = p2.getY() + h * (c2.getCenter().getX() - c1.getCenter().getX()) / dist;
        final Point2D resA = new Point2D(x31, y31);
        final Point2D resB = new Point2D(x32, y32);

        final List<Point2D> result = new LinkedList<>();
        if (resA.distance(resB) > 1e-10) {
            result.add(resB);
        }

        result.add(resA);
        return new GeometryResult(Collections.unmodifiableList(result));
    }

    public static GeometryResult<ArcCircle> intersection(final Circle c1, final ArcCircle ac1) {
        return null;
    }

    public static GeometryResult<Circle> intersection(final Circle c1, final Ellipse e1) {
        return null;
    }

    public static GeometryResult<ArcCircle> intersection(final Circle c1, final ArcEllipse e1) {
        return null;
    }

    public static GeometryResult<ArcCircle> intersection(final ArcCircle ac1, final ArcCircle ac2) {
        return null;
    }

    public static GeometryResult<ArcCircle> intersection(final ArcCircle ac1, final Ellipse e1) {
        return null;
    }

    public static GeometryResult<ArcCircle> intersection(final ArcCircle ac1, final ArcEllipse e1) {
        return null;
    }

    public static GeometryResult<Ellipse> intersection(final Ellipse e1, final Ellipse e2) {
        return null;
    }

    public static GeometryResult<ArcEllipse> intersection(final Ellipse e1, final ArcEllipse ae1) {
        return null;
    }

    public static GeometryResult<ArcEllipse> intersection(final ArcEllipse e1, final ArcEllipse ae1) {
        return null;
    }

    // TODO in 'innere' und 'äußere' Tangenten unterteilen, wenn die zwei Kreise sich überlappen, können nur noch die 'äußeren' Berechnet werden, wenn der eine Kreis den anderen einschließt kann keine Tangente merh berechnet werden
    //

    /**
     * @param c1
     * @param c2
     * @return
     * @see <a href="https://en.wikipedia.org/wiki/Tangent_lines_to_circles">Tangent lines to circles</a>
     */
    public static Optional<List<Segment>> tangents(final Circle c1, final Circle c2) {
        if (c1 == null || c2 == null) {
            throw new IllegalArgumentException("arguments mut not be null");
        }

        final double dist = c1.getCenter().distance(c2.getCenter());
        final double alpha = Math.PI * 0.5 - Math.asin(Math.abs(c1.getRadius() - c2.getRadius()) / dist);
        final double beta = Math.PI * 0.5 - Math.asin((c1.getRadius() + c2.getRadius()) / dist);
        double angle = c1.getCenter().diff(c2.getCenter()).angle();

        Circle tmpA, tmpB;
        if (c1.getRadius() >= c2.getRadius()) {
            tmpA = c1;
            tmpB = c2;
        } else {
            angle -= Math.PI;
            tmpA = c2;
            tmpB = c1;
        }

        if (tmpA.getCenter().distance(tmpB.getCenter()) <= tmpB.getRadius()) {
            return Optional.empty();
        }

        final List<Segment> result = new ArrayList(2);
        // outer tangents
        final Point2D p1A = tmpA.getCenter().pointAt(angle + alpha, tmpA.getRadius());
        final Point2D p1B = tmpA.getCenter().pointAt(angle - alpha, tmpA.getRadius());
        final Point2D p2A = tmpB.getCenter().pointAt(angle + alpha, tmpB.getRadius());
        final Point2D p2B = tmpB.getCenter().pointAt(angle - alpha, tmpB.getRadius());
        result.add(new Segment(p1A, p2A));
        result.add(new Segment(p1B, p2B));

        final GeometryResult<Circle> inter = intersection(tmpA, tmpB);
        if (!inter.getPoints().isPresent()) {
            // inner tangents
            final Point2D p1C = tmpA.getCenter().pointAt(angle + beta, tmpA.getRadius());
            final Point2D p1D = tmpA.getCenter().pointAt(angle - beta, tmpA.getRadius());
            final Point2D p2C = tmpB.getCenter().pointAt(angle + (beta + Math.PI), tmpB.getRadius());
            final Point2D p2D = tmpB.getCenter().pointAt(angle - (beta + Math.PI), tmpB.getRadius());

            result.add(new Segment(p1C, p2C));
            result.add(new Segment(p1D, p2D));
        }

        return Optional.of(result);
    }

    public static GeometryResult<Segment> union(final Segment s1, final Segment s2) {
        // TODO two segements can be joined if the are on the same line ? so just compute the intersection, if this exists compute if the lines are collinear, then they can be joined
        return null;
    }

    public static boolean within(final Point2D pt, final Circle c1) {
        return c1.getCenter().distance(pt) < c1.getRadius();
    }

    // TODO testcase für Grenzfall, wenn start bei 0 und end ebei 180 ist und der Punkt genau auf y=0 dann gibt es eine Geometry zurück und kein Punkt
    public static boolean within(final Point2D pt, final ArcCircle a1) {
        final Segment s1 = new Segment(a1.getArcStart(), a1.getArcEnd());
        final Segment s2 = new Segment(a1.getCenter(), pt);
        final GeometryResult<Segment> intersection = GeometryOperation.intersection(s1, s2);
        final boolean result = (intersection.getGeometry().isPresent() || intersection.getPoints().isPresent()) && within(pt, (Circle) a1);

        if (Math.abs(a1.getArcLength()) > Math.PI) {
            return !result;
        }
        return result;
    }

    /**
     * if the point is on the segemtn it will be inserted afterwards two segment will be returned
     *
     * @param s1
     * @param pt
     * @return
     */

    public static Optional<List<Segment>> insertPoint(final Segment s1, final Point2D pt) {
        return Optional.empty();
    }

}

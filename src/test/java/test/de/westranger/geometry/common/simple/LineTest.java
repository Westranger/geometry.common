package test.de.westranger.geometry.common.simple;

import de.westranger.geometry.common.math.Rotation2D;
import de.westranger.geometry.common.math.Vector2D;
import de.westranger.geometry.common.simple.ArcCircle;
import de.westranger.geometry.common.simple.Circle;
import de.westranger.geometry.common.simple.Line;
import de.westranger.geometry.common.simple.Point2D;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {

    @Test
    void testIntersectionLineLineOneIntersection() {
        // http://zonalandeducation.com/mmts/intersections/intersectionOfTwoLines1/intersectionOfTwoLines1.html

        // 2x+3
        final Point2D pt11 = new Point2D(2.0, 7.0);
        final Point2D pt12 = new Point2D(4.0, 11.0);

        // -0.5x+7
        final Point2D pt21 = new Point2D(2.0, 6.0);
        final Point2D pt22 = new Point2D(4.0, 5.0);

        final Line l1 = new Line(pt11, pt12);
        final Line l2 = new Line(pt21, pt22);

        final OptionalDouble intersection = l1.intersection(l2);
        assertTrue(intersection.isPresent());

        assertEquals(-0.2, intersection.getAsDouble(), 1e-10);

        final Vector2D result = l1.getPositionVector().add(l1.getDirectionVector().lerp(intersection.getAsDouble()));
        assertEquals(1.6, result.getX(), 1e-10);
        assertEquals(6.2, result.getY(), 1e-10);
    }

    @Test
    void testIntersectionLineLineColinear() {
        final Point2D pt11 = new Point2D(2.0, 7.0);
        final Point2D pt12 = new Point2D(4.0, 11.0);

        final Line l1 = new Line(pt11, pt12);

        final OptionalDouble intersection = l1.intersection(l1);
        assertTrue(intersection.isPresent());
        assertEquals(Double.POSITIVE_INFINITY, intersection.getAsDouble(), 1e-10);
    }

    @Test
    void testIntersectionLineLineParallel() {
        final Point2D pt11 = new Point2D(2.0, 7.0);
        final Point2D pt12 = new Point2D(4.0, 11.0);

        final Point2D pt21 = new Point2D(3.0, 7.0);
        final Point2D pt22 = new Point2D(5.0, 11.0);

        final Line l1 = new Line(pt11, pt12);
        final Line l2 = new Line(pt21, pt22);

        final OptionalDouble intersection = l1.intersection(l2);
        assertFalse(intersection.isPresent());
    }

    @Test
    void testClosestPointOnLine() {
        final Point2D pt11 = new Point2D(3.0, 0.0);
        final Point2D pt12 = new Point2D(7.0, 4.0);
        final Point2D pt = new Point2D(2.0, 5.0);

        final Line l1 = new Line(pt11, pt12);
        final Point2D result = l1.getClosestPointOnLine(pt);

        assertEquals(5.0, result.getX(), 1e-10);
        assertEquals(2.0, result.getY(), 1e-10);
    }

    @Test
    void testIntersectionLineCircleTwoPoints() {
        final Point2D ptCircleCenter = new Point2D(2.0, 5.0);
        final Point2D ptLineCircleIntersection = new Point2D(5.0, 2.0);

        final Point2D pt1 = new Point2D(3.0, 0.0);
        final Point2D pt2 = new Point2D(7.0, 4.0);

        final double a = Math.sqrt((2.0 * 2.0) + (2.0 * 2.0));
        final double b = ptCircleCenter.diff(ptLineCircleIntersection).norm();
        final double radius = Math.sqrt((a * a) + (b * b));

        final Line l1 = new Line(pt1, pt2);
        final Circle circle = new Circle(ptCircleCenter, radius);

        final Optional<List<Point2D>> intersection = l1.intersection(circle);

        assertTrue(intersection.isPresent());
        assertEquals(2, intersection.get().size());

        assertEquals(7.0, intersection.get().get(0).getX(), 1e-10);
        assertEquals(4.0, intersection.get().get(0).getY(), 1e-10);

        assertEquals(3.0, intersection.get().get(1).getX(), 1e-10);
        assertEquals(0.0, intersection.get().get(1).getY(), 1e-10);
    }

    @Test
    void testIntersectionLineCircleOnePoint() {
        final Point2D ptCircleCenter = new Point2D(2.0, 5.0);
        final Point2D ptLineCircleIntersection = new Point2D(5.0, 2.0);

        final Point2D pt1 = new Point2D(3.0, 0.0);
        final Point2D pt2 = new Point2D(7.0, 4.0);

        final double radius = ptCircleCenter.diff(ptLineCircleIntersection).norm();

        final Line l1 = new Line(pt1, pt2);
        final Circle circle = new Circle(ptCircleCenter, radius);

        final Optional<List<Point2D>> intersection = l1.intersection(circle);

        assertTrue(intersection.isPresent());
        assertEquals(1, intersection.get().size());

        assertEquals(ptLineCircleIntersection.getX(), intersection.get().get(0).getX(), 1e-10);
        assertEquals(ptLineCircleIntersection.getY(), intersection.get().get(0).getY(), 1e-10);
    }

    @Test
    void testIntersectionLineCircleNoPoint() {
        final Point2D ptCircleCenter = new Point2D(2.0, 5.0);
        final Point2D ptLineCircleIntersection = new Point2D(5.0, 2.0);

        final Point2D pt1 = new Point2D(3.0, 0.0);
        final Point2D pt2 = new Point2D(7.0, 4.0);

        final Line l1 = new Line(pt1, pt2);
        final Circle circle = new Circle(ptCircleCenter, 1.0);

        final Optional<List<Point2D>> intersection = l1.intersection(circle);

        assertFalse(intersection.isPresent());
    }

    @Test
    void testIntersectionLineArcPositive() {
        final Point2D ptCircleCenter = new Point2D(0.0, 0.0);

        final Point2D pt11 = new Point2D(-2.0, -2.0);
        final Point2D pt12 = new Point2D(2.0, 2.0);

        final Point2D pt21 = new Point2D(2.0, -2.0);
        final Point2D pt22 = new Point2D(-2.0, 2.0);

        final Point2D ptArcStart = new Point2D(0.0, -2.0);
        final Point2D ptArcEnd = new Point2D(2.0, 0.0);

        final Line l1 = new Line(pt11, pt12);
        final Line l2 = new Line(pt21, pt22);
        final ArcCircle arc = new ArcCircle(ptCircleCenter, ptArcStart, ptArcEnd, -Math.PI * 0.5, Math.PI * 0.5, 2.0);

        Optional<List<Point2D>> intersection = l1.intersection(arc);
        assertFalse(intersection.isPresent());

        intersection = l2.intersection(arc);
        assertTrue(intersection.isPresent());
        assertEquals(1, intersection.get().size());

        final Vector2D result = (new Rotation2D(-Math.PI * 0.25)).multiply(new Vector2D(2.0, 0.0));
        assertEquals(result.getX(), intersection.get().get(0).getX(), 1e-10);
        assertEquals(result.getY(), intersection.get().get(0).getY(), 1e-10);
    }

    @Test
    void testIntersectionLineArcNegative() {
        final Point2D ptCircleCenter = new Point2D(0.0, 0.0);

        final Point2D pt11 = new Point2D(-2.0, -2.0);
        final Point2D pt12 = new Point2D(2.0, 2.0);

        final Point2D pt21 = new Point2D(2.0, -2.0);
        final Point2D pt22 = new Point2D(-2.0, 2.0);

        final Point2D ptArcStart = new Point2D(2.0, 0.0);
        final Point2D ptArcEnd = new Point2D(0.0, -2.0);

        final Line l1 = new Line(pt11, pt12);
        final Line l2 = new Line(pt21, pt22);
        final ArcCircle arc = new ArcCircle(ptCircleCenter, ptArcStart, ptArcEnd, 0.0, -Math.PI * 0.5, 2.0);

        Optional<List<Point2D>> intersection = l1.intersection(arc);
        assertFalse(intersection.isPresent());

        intersection = l2.intersection(arc);
        assertTrue(intersection.isPresent());
        assertEquals(1, intersection.get().size());

        final Vector2D result = (new Rotation2D(-Math.PI * 0.25)).multiply(new Vector2D(2.0, 0.0));
        assertEquals(result.getX(), intersection.get().get(0).getX(), 1e-10);
        assertEquals(result.getY(), intersection.get().get(0).getY(), 1e-10);
    }

}
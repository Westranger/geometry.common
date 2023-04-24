package test.de.westranger.geometry.common.simple;

import de.westranger.geometry.common.operation.GeometryOperation;
import de.westranger.geometry.common.operation.result.GeometryResult;
import de.westranger.geometry.common.simple.Point2D;
import de.westranger.geometry.common.simple.Segment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SegmentTest {

    @Test
    void testClosestPointOnSegmentA() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(5.0, 3.0);

        final Point2D pt3 = new Point2D(3.0, 3.0);
        final Segment s1 = new Segment(pt1, pt2);

        final Point2D closestPointOnLine = s1.getClosestPointOnLine(pt3);

        assertEquals(3.4, closestPointOnLine.getX(), 1e-10);
        assertEquals(2.2, closestPointOnLine.getY(), 1e-10);
    }

    @Test
    void testClosestPointOnSegmentB() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(5.0, 3.0);

        final Point2D pt3 = new Point2D(5.0, 1.0);
        final Segment s1 = new Segment(pt1, pt2);

        final Point2D closestPointOnLine = s1.getClosestPointOnLine(pt3);

        assertEquals(4.2, closestPointOnLine.getX(), 1e-10);
        assertEquals(2.6, closestPointOnLine.getY(), 1e-10);
    }

    @Test
    void testClosestPointOnSegmentBeforePT1() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(5.0, 3.0);

        final Point2D pt3 = new Point2D(0.0, 0.0);
        final Segment s1 = new Segment(pt1, pt2);

        final Point2D closestPointOnLine = s1.getClosestPointOnLine(pt3);

        assertEquals(1.0, closestPointOnLine.getX(), 1e-10);
        assertEquals(1.0, closestPointOnLine.getY(), 1e-10);
    }

    @Test
    void testClosestPointOnSegmentAfterPT2() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(5.0, 3.0);

        final Point2D pt3 = new Point2D(6.0, 4.0);
        final Segment s1 = new Segment(pt1, pt2);

        final Point2D closestPointOnLine = s1.getClosestPointOnLine(pt3);

        assertEquals(5.0, closestPointOnLine.getX(), 1e-10);
        assertEquals(3.0, closestPointOnLine.getY(), 1e-10);
    }

    @Test
    void testClosestPointInfinityXComponent() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(5.0, 3.0);

        final Point2D pt3 = new Point2D(Double.NEGATIVE_INFINITY, 3.0);
        final Segment s1 = new Segment(pt1, pt2);

        assertThrows(IllegalArgumentException.class, () -> s1.getClosestPointOnLine(pt3));
    }

    @Test
    void testClosestPointInfinityYComponent() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(5.0, 3.0);

        final Point2D pt3 = new Point2D(5.0, Double.NEGATIVE_INFINITY);
        final Segment s1 = new Segment(pt1, pt2);

        assertThrows(IllegalArgumentException.class, () -> s1.getClosestPointOnLine(pt3));
    }

    @Test
    void testSegmentIntersection01() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(2.0, 2.0);
        final Point2D pt3 = new Point2D(3.0, 3.0);
        final Point2D pt4 = new Point2D(4.0, 4.0);

        final Segment s1 = new Segment(pt1, pt2);
        final Segment s2 = new Segment(pt3, pt4);

        final GeometryResult<Segment> result = GeometryOperation.intersection(s1, s2);
        assertFalse(result.getPoints().isPresent());
        assertFalse(result.getGeometry().isPresent());
    }

    @Test
    void testSegmentIntersection02() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(2.0, 2.0);
        final Point2D pt3 = new Point2D(3.0, 3.0);
        final Point2D pt4 = new Point2D(4.0, 4.0);

        final Segment s1 = new Segment(pt1, pt2);
        final Segment s2 = new Segment(pt4, pt3);

        final GeometryResult<Segment> result = GeometryOperation.intersection(s1, s2);
        assertFalse(result.getPoints().isPresent());
        assertFalse(result.getGeometry().isPresent());
    }

    @Test
    void testSegmentIntersection03() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(2.0, 2.0);
        final Point2D pt3 = new Point2D(3.0, 3.0);
        final Point2D pt4 = new Point2D(4.0, 4.0);

        final Segment s1 = new Segment(pt1, pt4);
        final Segment s2 = new Segment(pt2, pt3);

        final GeometryResult<Segment> result = GeometryOperation.intersection(s1, s2);
        assertFalse(result.getPoints().isPresent());
        assertTrue(result.getGeometry().isPresent());

        final Segment segment = result.getGeometry().get();
        assertEquals(0.0, pt2.distance(segment.getStart()), 1e-10);
        assertEquals(0.0, pt3.distance(segment.getEnd()), 1e-10);
    }

    @Test
    void testSegmentIntersection04() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(2.0, 2.0);
        final Point2D pt3 = new Point2D(3.0, 3.0);
        final Point2D pt4 = new Point2D(4.0, 4.0);

        final Segment s1 = new Segment(pt1, pt3);
        final Segment s2 = new Segment(pt2, pt4);

        final GeometryResult<Segment> result = GeometryOperation.intersection(s1, s2);
        assertFalse(result.getPoints().isPresent());
        assertTrue(result.getGeometry().isPresent());

        final Segment segment = result.getGeometry().get();
        assertEquals(0.0, pt2.distance(segment.getStart()), 1e-10);
        assertEquals(0.0, pt3.distance(segment.getEnd()), 1e-10);
    }

    @Test
    void testSegmentIntersection05() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(2.0, 2.0);
        final Point2D pt3 = new Point2D(3.0, 3.0);
        final Point2D pt4 = new Point2D(4.0, 4.0);

        final Segment s1 = new Segment(pt1, pt3);
        final Segment s2 = new Segment(pt4, pt2);

        final GeometryResult<Segment> result = GeometryOperation.intersection(s1, s2);
        assertFalse(result.getPoints().isPresent());
        assertTrue(result.getGeometry().isPresent());

        final Segment segment = result.getGeometry().get();
        assertEquals(0.0, pt2.distance(segment.getStart()), 1e-10);
        assertEquals(0.0, pt3.distance(segment.getEnd()), 1e-10);
    }

    @Test
    void testSegmentIntersection06() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(2.0, 2.0);
        final Point2D pt3 = new Point2D(3.0, 3.0);
        final Point2D pt4 = new Point2D(4.0, 4.0);

        final Segment s1 = new Segment(pt1, pt4);
        final Segment s2 = new Segment(pt3, pt2);

        final GeometryResult<Segment> result = GeometryOperation.intersection(s1, s2);
        assertFalse(result.getPoints().isPresent());
        assertTrue(result.getGeometry().isPresent());

        final Segment segment = result.getGeometry().get();
        assertEquals(0.0, pt3.distance(segment.getStart()), 1e-10);
        assertEquals(0.0, pt2.distance(segment.getEnd()), 1e-10);
    }

    @Test
    void testSegmentIntersection07() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(2.0, 2.0);
        final Point2D pt3 = new Point2D(3.0, 3.0);
        final Point2D pt4 = new Point2D(4.0, 4.0);

        final Segment s1 = new Segment(pt2, pt1);
        final Segment s2 = new Segment(pt3, pt4);

        final GeometryResult<Segment> result = GeometryOperation.intersection(s1, s2);
        assertFalse(result.getPoints().isPresent());
        assertFalse(result.getGeometry().isPresent());
    }

    @Test
    void testSegmentIntersection08() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(2.0, 2.0);
        final Point2D pt3 = new Point2D(3.0, 3.0);
        final Point2D pt4 = new Point2D(4.0, 4.0);

        final Segment s1 = new Segment(pt2, pt1);
        final Segment s2 = new Segment(pt4, pt3);

        final GeometryResult<Segment> result = GeometryOperation.intersection(s1, s2);
        assertFalse(result.getPoints().isPresent());
        assertFalse(result.getGeometry().isPresent());
    }

    @Test
    void testSegmentIntersection09() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(2.0, 2.0);
        final Point2D pt3 = new Point2D(3.0, 3.0);
        final Point2D pt4 = new Point2D(4.0, 4.0);

        final Segment s1 = new Segment(pt4, pt1);
        final Segment s2 = new Segment(pt2, pt3);

        final GeometryResult<Segment> result = GeometryOperation.intersection(s1, s2);
        assertFalse(result.getPoints().isPresent());
        assertTrue(result.getGeometry().isPresent());

        final Segment segment = result.getGeometry().get();
        assertEquals(0.0, pt2.distance(segment.getStart()), 1e-10);
        assertEquals(0.0, pt3.distance(segment.getEnd()), 1e-10);
    }

    @Test
    void testSegmentIntersection010() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(2.0, 2.0);
        final Point2D pt3 = new Point2D(3.0, 3.0);
        final Point2D pt4 = new Point2D(4.0, 4.0);

        final Segment s1 = new Segment(pt3, pt1);
        final Segment s2 = new Segment(pt2, pt4);

        final GeometryResult<Segment> result = GeometryOperation.intersection(s1, s2);
        assertFalse(result.getPoints().isPresent());
        assertTrue(result.getGeometry().isPresent());

        final Segment segment = result.getGeometry().get();
        assertEquals(0.0, pt2.distance(segment.getStart()), 1e-10);
        assertEquals(0.0, pt3.distance(segment.getEnd()), 1e-10);
    }

    @Test
    void testSegmentIntersection11() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(2.0, 2.0);
        final Point2D pt3 = new Point2D(3.0, 3.0);
        final Point2D pt4 = new Point2D(4.0, 4.0);

        final Segment s1 = new Segment(pt3, pt1);
        final Segment s2 = new Segment(pt4, pt2);

        final GeometryResult<Segment> result = GeometryOperation.intersection(s1, s2);
        assertFalse(result.getPoints().isPresent());
        assertTrue(result.getGeometry().isPresent());

        final Segment segment = result.getGeometry().get();
        assertEquals(0.0, pt2.distance(segment.getStart()), 1e-10);
        assertEquals(0.0, pt3.distance(segment.getEnd()), 1e-10);
    }

    @Test
    void testSegmentIntersection12() {
        final Point2D pt1 = new Point2D(1.0, 1.0);
        final Point2D pt2 = new Point2D(2.0, 2.0);
        final Point2D pt3 = new Point2D(3.0, 3.0);
        final Point2D pt4 = new Point2D(4.0, 4.0);

        final Segment s1 = new Segment(pt4, pt1);
        final Segment s2 = new Segment(pt3, pt2);

        final GeometryResult<Segment> result = GeometryOperation.intersection(s1, s2);
        assertFalse(result.getPoints().isPresent());
        assertTrue(result.getGeometry().isPresent());

        final Segment segment = result.getGeometry().get();
        assertEquals(0.0, pt3.distance(segment.getStart()), 1e-10);
        assertEquals(0.0, pt2.distance(segment.getEnd()), 1e-10);
    }

    @Test
    void testSegmentIntersectionYPartZero() {
        final Point2D pt1 = new Point2D(1.0, 0.0);
        final Point2D pt2 = new Point2D(2.0, 0.0);
        final Point2D pt3 = new Point2D(3.0, 0.0);
        final Point2D pt4 = new Point2D(4.0, 0.0);

        final Segment s1 = new Segment(pt4, pt1);
        final Segment s2 = new Segment(pt3, pt2);

        final GeometryResult<Segment> result = GeometryOperation.intersection(s1, s2);
        assertFalse(result.getPoints().isPresent());
        assertTrue(result.getGeometry().isPresent());

        final Segment segment = result.getGeometry().get();
        assertEquals(0.0, pt3.distance(segment.getStart()), 1e-10);
        assertEquals(0.0, pt2.distance(segment.getEnd()), 1e-10);
    }

    @Test
    void testSegmentIntersectionXPartZero() {
        final Point2D pt1 = new Point2D(0.0, 1.0);
        final Point2D pt2 = new Point2D(0.0, 2.0);
        final Point2D pt3 = new Point2D(0.0, 3.0);
        final Point2D pt4 = new Point2D(0.0, 4.0);

        final Segment s1 = new Segment(pt4, pt1);
        final Segment s2 = new Segment(pt3, pt2);

        final GeometryResult<Segment> result = GeometryOperation.intersection(s1, s2);
        assertFalse(result.getPoints().isPresent());
        assertTrue(result.getGeometry().isPresent());

        final Segment segment = result.getGeometry().get();
        assertEquals(0.0, pt3.distance(segment.getStart()), 1e-10);
        assertEquals(0.0, pt2.distance(segment.getEnd()), 1e-10);
    }

}

package test.de.westranger.geometry.common.simple;

import de.westranger.geometry.common.simple.Point2D;
import de.westranger.geometry.common.simple.Segment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

}

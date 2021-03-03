package test.de.westranger.geometry.common.simple;

import de.westranger.geometry.common.math.Vector2D;
import de.westranger.geometry.common.simple.Line;
import de.westranger.geometry.common.simple.Point2D;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void intersection() {
    }

    @Test
    void getClosestPointOnLine() {
    }

    @Test
    void testIntersection() {
        // http://zonalandeducation.com/mmts/intersections/intersectionOfTwoLines1/intersectionOfTwoLines1.html

        // 2x+3
        final Point2D pt11 = new Point2D(2.0, 7.0);
        final Point2D pt12 = new Point2D(4.0, 11.0);

        // -0.5x+7
        final Point2D pt21 = new Point2D(2.0, 6.0);
        final Point2D pt22 = new Point2D(4.0, 3.0);

        final Line l1 = new Line(pt11, pt12);
        final Line l2 = new Line(pt21, pt22);

        final OptionalDouble intersection = l1.intersection(l2);
        assertTrue(intersection.isPresent());

        final Vector2D result = l1.getPositionVector().lerp(1.0+intersection.getAsDouble());
        assertEquals(1.6, result.getX(), 1e-10);
        assertEquals(6.2, result.getY(), 1e-10);
    }

    @Test
    void testIntersection1() {
    }

    @Test
    void testIntersection2() {
    }

    @Test
    void testIntersection3() {
    }

    @Test
    void getPositionVector() {
    }

    @Test
    void getDirectionVector() {
    }
}
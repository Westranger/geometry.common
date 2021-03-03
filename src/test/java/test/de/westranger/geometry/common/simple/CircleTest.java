package test.de.westranger.geometry.common.simple;

import de.westranger.geometry.common.simple.Circle;
import de.westranger.geometry.common.simple.Point2D;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

final class CircleTest {

    @Test
    void intersectionA() {
        final Circle c1 = new Circle(new Point2D(0.0, 0.0), 3.0);
        final Circle c2 = new Circle(new Point2D(3.0, 3.0), 3.0);

        final Optional<List<Point2D>> intersection = c1.intersection(c2);
        assertTrue(intersection.isPresent());
        assertEquals(2, intersection.get().size());

        final Point2D pt1 = intersection.get().get(0);
        final Point2D pt2 = intersection.get().get(1);

        assertEquals(0.0, pt1.getX(), 1e-10);
        assertEquals(3.0, pt1.getY(), 1e-10);
        assertEquals(3.0, pt2.getX(), 1e-10);
        assertEquals(0.0, pt2.getY(), 1e-10);
    }

    @Test
    void intersectionB() {
        final Circle c1 = new Circle(new Point2D(2.0, 1.0), 3.0);
        final Circle c2 = new Circle(new Point2D(-3.0, -4.0), 5.0);

        final Optional<List<Point2D>> intersection = c1.intersection(c2);
        assertTrue(intersection.isPresent());
        assertEquals(2, intersection.get().size());

        final Point2D pt1 = intersection.get().get(0);
        final Point2D pt2 = intersection.get().get(1);

        assertEquals(1.5688577540449509, pt1.getX(), 1e-10);
        assertEquals(-1.9688577540449517, pt1.getY(), 1e-10);
        assertEquals(-0.9688577540449517, pt2.getX(), 1e-10);
        assertEquals(0.5688577540449509, pt2.getY(), 1e-10);
    }

    @Test
    void intersectionSinglePoint() {
        final Circle c1 = new Circle(new Point2D(0.0, 0.0), 3.0);
        final Circle c2 = new Circle(new Point2D(5.0, 0.0), 2.0);

        final Optional<List<Point2D>> intersection = c1.intersection(c2);
        assertTrue(intersection.isPresent());
        assertEquals(1, intersection.get().size());

        final Point2D pt1 = intersection.get().get(0);

        assertEquals(3.0, pt1.getX(), 1e-10);
        assertEquals(0.0, pt1.getY(), 1e-10);
    }
}
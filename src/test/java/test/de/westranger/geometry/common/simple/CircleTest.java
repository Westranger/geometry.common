package test.de.westranger.geometry.common.simple;

import de.westranger.geometry.common.operation.GeometryOperation;
import de.westranger.geometry.common.operation.result.GeometryResult;
import de.westranger.geometry.common.plot.Colors;
import de.westranger.geometry.common.plot.SVGGenerator;
import de.westranger.geometry.common.simple.Circle;
import de.westranger.geometry.common.simple.Point2D;
import de.westranger.geometry.common.simple.Segment;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class CircleTest {

    @Test
    void intersectionA() {
        final Circle c1 = new Circle(new Point2D(0.0, 0.0), 3.0);
        final Circle c2 = new Circle(new Point2D(3.0, 3.0), 3.0);

        final GeometryResult<Circle> intersection = GeometryOperation.intersection(c1, c2);
        assertTrue(!intersection.getGeometry().isPresent());
        assertTrue(intersection.getPoints().isPresent());
        assertEquals(2, intersection.getPoints().get().size());

        final Point2D pt1 = intersection.getPoints().get().get(0);
        final Point2D pt2 = intersection.getPoints().get().get(1);

        assertEquals(0.0, pt1.getX(), 1e-10);
        assertEquals(3.0, pt1.getY(), 1e-10);
        assertEquals(3.0, pt2.getX(), 1e-10);
        assertEquals(0.0, pt2.getY(), 1e-10);
    }

    @Test
    void intersectionB() {
        final Circle c1 = new Circle(new Point2D(2.0, 1.0), 3.0);
        final Circle c2 = new Circle(new Point2D(-3.0, -4.0), 5.0);

        final GeometryResult<Circle> intersection = GeometryOperation.intersection(c1, c2);
        assertTrue(!intersection.getGeometry().isPresent());
        assertTrue(intersection.getPoints().isPresent());
        assertEquals(2, intersection.getPoints().get().size());

        final Point2D pt1 = intersection.getPoints().get().get(0);
        final Point2D pt2 = intersection.getPoints().get().get(1);

        assertEquals(1.5688577540449509, pt1.getX(), 1e-10);
        assertEquals(-1.9688577540449517, pt1.getY(), 1e-10);
        assertEquals(-0.9688577540449517, pt2.getX(), 1e-10);
        assertEquals(0.5688577540449509, pt2.getY(), 1e-10);
    }

    @Test
    void intersectionSinglePoint() {
        final Circle c1 = new Circle(new Point2D(0.0, 0.0), 3.0);
        final Circle c2 = new Circle(new Point2D(5.0, 0.0), 2.0);

        final GeometryResult<Circle> intersection = GeometryOperation.intersection(c1, c2);
        assertTrue(!intersection.getGeometry().isPresent());
        assertTrue(intersection.getPoints().isPresent());
        assertEquals(1, intersection.getPoints().get().size());

        final Point2D pt1 = intersection.getPoints().get().get(0);

        assertEquals(3.0, pt1.getX(), 1e-10);
        assertEquals(0.0, pt1.getY(), 1e-10);
    }

    @Test
    void tangentsTwoCircles() {
        final Circle c1 = new Circle(new Point2D(0.0, 0.0), 3.0);
        final Circle c2 = new Circle(new Point2D(6.0, 1.0), 2.0);

        final Optional<List<Segment>> tangents = GeometryOperation.tangents(c1, c2);
        assertTrue(tangents.isPresent());
        assertEquals(4, tangents.get().size());

        for (Segment seg : tangents.get()) {
            assertEquals(Math.PI * 0.5, Point2D.angleBetween(c1.getCenter(), seg.getStart(), seg.getEnd()), 1e-10);
        }

        for (Segment seg : tangents.get()) {
            assertEquals(Math.PI * 0.5, Point2D.angleBetween(c2.getCenter(), seg.getEnd(), seg.getStart()), 1e-10);
        }

        final Segment s1 = tangents.get().get(0);
        final Segment s2 = tangents.get().get(1);
        final Segment s3 = tangents.get().get(2);
        final Segment s4 = tangents.get().get(3);

        final SVGGenerator gen = new SVGGenerator(1000, 1000);
        gen.add(c1, Colors.BLUE, 0.05);
        gen.add(c2, Colors.BLUE, 0.05);

        gen.add(s1, Colors.GREEN, 0.05);
        gen.add(s2, Colors.LIME, 0.05);
        gen.add(s3, Colors.MAGENTA, 0.05);
        gen.add(s4, Colors.PURPLE, 0.05);

        gen.writeFile("./tangents_two_circles.svg");
    }

    @Test
    void tangentsTwoCirclesParameterSwitch() {
        final Circle c1 = new Circle(new Point2D(6.0, 1.0), 2.0);
        final Circle c2 = new Circle(new Point2D(0.0, 0.0), 3.0);

        final Optional<List<Segment>> tangents = GeometryOperation.tangents(c1, c2);
        assertTrue(tangents.isPresent());
        assertEquals(4, tangents.get().size());

        for (Segment seg : tangents.get()) {
            assertEquals(Math.PI * 0.5, Point2D.angleBetween(c1.getCenter(), seg.getEnd(), seg.getStart()), 1e-10);
        }

        for (Segment seg : tangents.get()) {
            assertEquals(Math.PI * 0.5, Point2D.angleBetween(c2.getCenter(), seg.getStart(), seg.getEnd()), 1e-10);
        }

        final Segment s1 = tangents.get().get(0);
        final Segment s2 = tangents.get().get(1);
        final Segment s3 = tangents.get().get(2);
        final Segment s4 = tangents.get().get(3);

        final SVGGenerator gen = new SVGGenerator(1000, 1000);
        gen.add(c1, Colors.BLUE, 0.05);
        gen.add(c2, Colors.BLUE, 0.05);

        gen.add(s1, Colors.GREEN, 0.05);
        gen.add(s2, Colors.LIME, 0.05);
        gen.add(s3, Colors.MAGENTA, 0.05);
        gen.add(s4, Colors.PURPLE, 0.05);

        gen.writeFile("./tangents_two_circles_parameter_switch.svg");
    }

    @Test
    void tangentsTwoCirclesSideSwitch() {
        final Circle c1 = new Circle(new Point2D(6.0, 1.0), 2.0);
        final Circle c2 = new Circle(new Point2D(12.0, -3.0), 3.0);

        final Optional<List<Segment>> tangents = GeometryOperation.tangents(c1, c2);
        assertTrue(tangents.isPresent());
        assertEquals(4, tangents.get().size());

        for (Segment seg : tangents.get()) {
            assertEquals(Math.PI * 0.5, Point2D.angleBetween(c1.getCenter(), seg.getEnd(), seg.getStart()), 1e-10);
        }

        for (Segment seg : tangents.get()) {
            assertEquals(Math.PI * 0.5, Point2D.angleBetween(c2.getCenter(), seg.getStart(), seg.getEnd()), 1e-10);
        }

        final Segment s1 = tangents.get().get(0);
        final Segment s2 = tangents.get().get(1);
        final Segment s3 = tangents.get().get(2);
        final Segment s4 = tangents.get().get(3);

        final SVGGenerator gen = new SVGGenerator(1000, 1000);
        gen.add(c1, Colors.BLUE, 0.05);
        gen.add(c2, Colors.BLUE, 0.05);

        gen.add(s1, Colors.GREEN, 0.05);
        gen.add(s2, Colors.LIME, 0.05);
        gen.add(s3, Colors.MAGENTA, 0.05);
        gen.add(s4, Colors.PURPLE, 0.05);

        gen.writeFile("./tangents_two_circles_side_switch.svg");
    }

    @Test
    void tangentsTwoCirclesEqualsSizes() {
        final Circle c1 = new Circle(new Point2D(6.0, 1.0), 2.0);
        final Circle c2 = new Circle(new Point2D(12.0, -3.0), 2.0);

        final Optional<List<Segment>> tangents = GeometryOperation.tangents(c1, c2);
        assertTrue(tangents.isPresent());
        assertEquals(4, tangents.get().size());

        for (Segment seg : tangents.get()) {
            assertEquals(Math.PI * 0.5, Point2D.angleBetween(c1.getCenter(), seg.getStart(), seg.getEnd()), 1e-10);
        }

        for (Segment seg : tangents.get()) {
            assertEquals(Math.PI * 0.5, Point2D.angleBetween(c2.getCenter(), seg.getEnd(), seg.getStart()), 1e-10);
        }

        final Segment s1 = tangents.get().get(0);
        final Segment s2 = tangents.get().get(1);
        final Segment s3 = tangents.get().get(2);
        final Segment s4 = tangents.get().get(3);

        final SVGGenerator gen = new SVGGenerator(1000, 1000);
        gen.add(c1, Colors.BLUE, 0.05);
        gen.add(c2, Colors.BLUE, 0.05);

        gen.add(s1, Colors.GREEN, 0.05);
        gen.add(s2, Colors.LIME, 0.05);
        gen.add(s3, Colors.MAGENTA, 0.05);
        gen.add(s4, Colors.PURPLE, 0.05);

        gen.writeFile("./tangents_two_circles_equal_sizes.svg");
    }

    @Test
    void tangentsTwoCirclesOuterTangentsOnly() {
        final Circle c1 = new Circle(new Point2D(6.0, 1.0), 3.0);
        final Circle c2 = new Circle(new Point2D(10.0, 2.0), 2.0);

        final Optional<List<Segment>> tangents = GeometryOperation.tangents(c1, c2);
        assertTrue(tangents.isPresent());
        assertEquals(2, tangents.get().size());

        for (Segment seg : tangents.get()) {
            assertEquals(Math.PI * 0.5, Point2D.angleBetween(c1.getCenter(), seg.getStart(), seg.getEnd()), 1e-10);
        }

        for (Segment seg : tangents.get()) {
            assertEquals(Math.PI * 0.5, Point2D.angleBetween(c2.getCenter(), seg.getEnd(), seg.getStart()), 1e-10);
        }

        final Segment s1 = tangents.get().get(0);
        final Segment s2 = tangents.get().get(1);


        final SVGGenerator gen = new SVGGenerator(1000, 1000);
        gen.add(c1, Colors.BLUE, 0.05);
        gen.add(c2, Colors.BLUE, 0.05);

        gen.add(s1, Colors.GREEN, 0.05);
        gen.add(s2, Colors.LIME, 0.05);

        gen.writeFile("./tangents_two_circles_outer_tangents_only.svg");
    }

    @Test
    void tangentsTwoCirclesNoTangents() {
        final Circle c1 = new Circle(new Point2D(6.0, 1.0), 3.0);
        final Circle c2 = new Circle(new Point2D(6.5, 1.5), 2.0);

        final Optional<List<Segment>> tangents = GeometryOperation.tangents(c1, c2);
        assertTrue(!tangents.isPresent());
    }

}

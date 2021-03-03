package test.de.westranger.geometry.common.complex;

import de.westranger.geometry.common.complex.CircleSegment;
import de.westranger.geometry.common.simple.Point2D;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircleSegmentTest {

    @org.junit.jupiter.api.Test
    void setRadiusAndMarginCW() {
        Point2D p1 = new Point2D(0.0, 0.0);
        Point2D p2 = new Point2D(0.0, 100.0); // 100
        Point2D p3 = new Point2D(200.0, 100.0); // 200
        Point2D p4 = new Point2D(200.0, -200.0); // 300
        Point2D p5 = new Point2D(-200.0, -200.0); // 400
        Point2D p6 = new Point2D(-200.0, 300.0); // 500

        final double radius = 50.0;

        CircleSegment cs1 = new CircleSegment(p1, p2, p3, radius);
        CircleSegment cs2 = new CircleSegment(p2, p3, p4, radius);
        CircleSegment cs3 = new CircleSegment(p3, p4, p5, radius);
        CircleSegment cs4 = new CircleSegment(p4, p5, p6, radius);

        assertEquals(-Math.PI * 0.5, cs1.getCenterCircle().getArcLength(), 1e-10);
        assertEquals(Math.PI, cs1.getCenterCircle().getArcStartAngle(), 1e-10);
        assertEquals(50.0, cs1.getCenterCircle().getCenter().getX(), 1e-10);
        assertEquals(50.0, cs1.getCenterCircle().getCenter().getY(), 1e-10);
        assertEquals(0.0, cs1.getCenterCircle().getArcStart().getX(), 1e-10);
        assertEquals(50.0, cs1.getCenterCircle().getArcStart().getY(), 1e-10);
        assertEquals(50.0, cs1.getCenterCircle().getArcEnd().getX(), 1e-10);
        assertEquals(100.0, cs1.getCenterCircle().getArcEnd().getY(), 1e-10);


        assertEquals(-Math.PI * 0.5, cs2.getCenterCircle().getArcLength(), 1e-10);
        assertEquals(Math.PI * 0.5, cs2.getCenterCircle().getArcStartAngle(), 1e-10);
        assertEquals(150.0, cs2.getCenterCircle().getCenter().getX(), 1e-10);
        assertEquals(50.0, cs2.getCenterCircle().getCenter().getY(), 1e-10);
        assertEquals(150.0, cs2.getCenterCircle().getArcStart().getX(), 1e-10);
        assertEquals(100.0, cs2.getCenterCircle().getArcStart().getY(), 1e-10);
        assertEquals(200.0, cs2.getCenterCircle().getArcEnd().getX(), 1e-10);
        assertEquals(50.0, cs2.getCenterCircle().getArcEnd().getY(), 1e-10);

        assertEquals(-Math.PI * 0.5, cs3.getCenterCircle().getArcLength(), 1e-10);
        assertEquals(0.0, cs3.getCenterCircle().getArcStartAngle(), 1e-10);
        assertEquals(150.0, cs3.getCenterCircle().getCenter().getX(), 1e-10);
        assertEquals(-150.0, cs3.getCenterCircle().getCenter().getY(), 1e-10);
        assertEquals(200.0, cs3.getCenterCircle().getArcStart().getX(), 1e-10);
        assertEquals(-150.0, cs3.getCenterCircle().getArcStart().getY(), 1e-10);
        assertEquals(150.0, cs3.getCenterCircle().getArcEnd().getX(), 1e-10);
        assertEquals(-200.0, cs3.getCenterCircle().getArcEnd().getY(), 1e-10);

        assertEquals(-Math.PI * 0.5, cs4.getCenterCircle().getArcLength(), 1e-10);
        assertEquals(-Math.PI * 0.5, cs4.getCenterCircle().getArcStartAngle(), 1e-10);
        assertEquals(-150.0, cs4.getCenterCircle().getCenter().getX(), 1e-10);
        assertEquals(-150.0, cs4.getCenterCircle().getCenter().getY(), 1e-10);
        assertEquals(-150.0, cs4.getCenterCircle().getArcStart().getX(), 1e-10);
        assertEquals(-200.0, cs4.getCenterCircle().getArcStart().getY(), 1e-10);
        assertEquals(-200.0, cs4.getCenterCircle().getArcEnd().getX(), 1e-10);
        assertEquals(-150.0, cs4.getCenterCircle().getArcEnd().getY(), 1e-10);
    }

    @org.junit.jupiter.api.Test
    void setRadiusAndMarginCCW() {
        Point2D p1 = new Point2D(-200.0, 300.0); // 500
        Point2D p2 = new Point2D(-200.0, -100.0); // 400
        Point2D p3 = new Point2D(200.0, -100.0); // 300
        Point2D p4 = new Point2D(200.0, 100.0); // 200
        Point2D p5 = new Point2D(0.0, 100.0); // 100
        Point2D p6 = new Point2D(0.0, 0.0);

        final double radius = 50.0;

        CircleSegment cs1 = new CircleSegment(p1, p2, p3, radius);
        CircleSegment cs2 = new CircleSegment(p2, p3, p4, radius);
        CircleSegment cs3 = new CircleSegment(p3, p4, p5, radius);
        CircleSegment cs4 = new CircleSegment(p4, p5, p6, radius);

        assertEquals(Math.PI * 0.5, cs1.getCenterCircle().getArcLength(), 1e-10);
        assertEquals(Math.PI, cs1.getCenterCircle().getArcStartAngle(), 1e-10);
        assertEquals(-150.0, cs1.getCenterCircle().getCenter().getX(), 1e-10);
        assertEquals(-50.0, cs1.getCenterCircle().getCenter().getY(), 1e-10);
        assertEquals(-200.0, cs1.getCenterCircle().getArcStart().getX(), 1e-10);
        assertEquals(-50.0, cs1.getCenterCircle().getArcStart().getY(), 1e-10);
        assertEquals(-150.0, cs1.getCenterCircle().getArcEnd().getX(), 1e-10);
        assertEquals(-100.0, cs1.getCenterCircle().getArcEnd().getY(), 1e-10);

        assertEquals(Math.PI * 0.5, cs2.getCenterCircle().getArcLength(), 1e-10);
        assertEquals(-Math.PI * 0.5, cs2.getCenterCircle().getArcStartAngle(), 1e-10);
        assertEquals(150.0, cs2.getCenterCircle().getCenter().getX(), 1e-10);
        assertEquals(-50.0, cs2.getCenterCircle().getCenter().getY(), 1e-10);
        assertEquals(150.0, cs2.getCenterCircle().getArcStart().getX(), 1e-10);
        assertEquals(-100.0, cs2.getCenterCircle().getArcStart().getY(), 1e-10);
        assertEquals(200.0, cs2.getCenterCircle().getArcEnd().getX(), 1e-10);
        assertEquals(-50.0, cs2.getCenterCircle().getArcEnd().getY(), 1e-10);

        assertEquals(Math.PI * 0.5, cs3.getCenterCircle().getArcLength(), 1e-10);
        assertEquals(0.0, cs3.getCenterCircle().getArcStartAngle(), 1e-10);
        assertEquals(150.0, cs3.getCenterCircle().getCenter().getX(), 1e-10);
        assertEquals(50.0, cs3.getCenterCircle().getCenter().getY(), 1e-10);
        assertEquals(200.0, cs3.getCenterCircle().getArcStart().getX(), 1e-10);
        assertEquals(50.0, cs3.getCenterCircle().getArcStart().getY(), 1e-10);
        assertEquals(150.0, cs3.getCenterCircle().getArcEnd().getX(), 1e-10);
        assertEquals(100.0, cs3.getCenterCircle().getArcEnd().getY(), 1e-10);

        assertEquals(Math.PI * 0.5, cs4.getCenterCircle().getArcLength(), 1e-10);
        assertEquals(Math.PI * 0.5, cs4.getCenterCircle().getArcStartAngle(), 1e-10);
        assertEquals(50.0, cs4.getCenterCircle().getCenter().getX(), 1e-10);
        assertEquals(50.0, cs4.getCenterCircle().getCenter().getY(), 1e-10);
        assertEquals(50.0, cs4.getCenterCircle().getArcStart().getX(), 1e-10);
        assertEquals(100.0, cs4.getCenterCircle().getArcStart().getY(), 1e-10);
        assertEquals(0.0, cs4.getCenterCircle().getArcEnd().getX(), 1e-10);
        assertEquals(50.0, cs4.getCenterCircle().getArcEnd().getY(), 1e-10);
    }
}

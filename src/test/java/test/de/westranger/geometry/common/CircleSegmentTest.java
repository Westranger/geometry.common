package test.de.westranger.geometry.common;

import de.westranger.geometry.common.complex.CircleSegment;
import de.westranger.geometry.common.math.Vector2D;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircleSegmentTest {

    @org.junit.jupiter.api.Test
    void setRadiusAndMarginCW() {
        Vector2D p1 = new Vector2D(0.0, 0.0);
        Vector2D p2 = new Vector2D(0.0, 100.0); // 100
        Vector2D p3 = new Vector2D(200.0, 100.0); // 200
        Vector2D p4 = new Vector2D(200.0, -200.0); // 300
        Vector2D p5 = new Vector2D(-200.0, -200.0); // 400
        Vector2D p6 = new Vector2D(-200.0, 300.0); // 500

        double radius = 50.0;
        double margin = 10.0;

        CircleSegment cs1 = new CircleSegment(p1, p2, p3, radius, margin);
        CircleSegment cs2 = new CircleSegment(p2, p3, p4, radius, margin);
        CircleSegment cs3 = new CircleSegment(p3, p4, p5, radius, margin);
        CircleSegment cs4 = new CircleSegment(p4, p5, p6, radius, margin);

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
        Vector2D p1 = new Vector2D(-200.0, 300.0); // 500
        Vector2D p2 = new Vector2D(-200.0, -100.0); // 400
        Vector2D p3 = new Vector2D(200.0, -100.0); // 300
        Vector2D p4 = new Vector2D(200.0, 100.0); // 200
        Vector2D p5 = new Vector2D(0.0, 100.0); // 100
        Vector2D p6 = new Vector2D(0.0, 0.0);

        double radius = 50.0;
        double margin = 10.0;

        CircleSegment cs1 = new CircleSegment(p1, p2, p3, radius, margin);
        CircleSegment cs2 = new CircleSegment(p2, p3, p4, radius, margin);
        CircleSegment cs3 = new CircleSegment(p3, p4, p5, radius, margin);
        CircleSegment cs4 = new CircleSegment(p4, p5, p6, radius, margin);

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

package test.de.westranger.geometry.common.math;

import de.westranger.geometry.common.math.Vector2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class Vector2DTest {

    @Test
    void testAdd() {
        final Vector2D vecA = new Vector2D(1.0, 2.0);
        final Vector2D vecB = new Vector2D(3.0, 1.0);

        final Vector2D result = vecA.add(vecB);

        assertEquals(4.0, result.getX(), 1e-10);
        assertEquals(3.0, result.getY(), 1e-10);
    }

    @Test
    void testSubtract() {
        final Vector2D vecA = new Vector2D(1.0, 2.0);
        final Vector2D vecB = new Vector2D(3.0, 1.0);

        final Vector2D result = vecA.subtract(vecB);

        assertEquals(-2.0, result.getX(), 1e-10);
        assertEquals(1.0, result.getY(), 1e-10);
    }

    @Test
    void testMultiply() {
        final Vector2D vecA = new Vector2D(1.0, 2.0);

        final Vector2D result = vecA.multiply(5.0);

        assertEquals(5.0, result.getX(), 1e-10);
        assertEquals(10.0, result.getY(), 1e-10);
    }

    @Test
    void testNorm() {
        final Vector2D vecA = new Vector2D(3.0, 4.0);

        assertEquals(5.0, vecA.norm(), 1e-10);
    }

    @Test
    void testDot() {
        final Vector2D vecA = new Vector2D(1.0, 2.0);
        final Vector2D vecB = new Vector2D(3.0, 5.0);

        assertEquals(1.0 * 3.0 + 2.0 * 5.0, vecA.dot(vecB), 1e-10);
    }

    @Test
    void testNormalize() {
        final Vector2D vecA = new Vector2D(3.0, 4.0);

        final Vector2D result = vecA.normalize();

        assertEquals(3.0 / 5.0, result.getX(), 1e-10);
        assertEquals(4.0 / 5.0, result.getY(), 1e-10);
    }

    @Test
    void testCross() {
        final Vector2D vecA = new Vector2D(1.0, 2.0);
        final Vector2D vecB = new Vector2D(3.0, 5.0);

        assertEquals(1.0 * 5.0 - 3.0 * 2.0, vecA.cross(vecB), 1e-10);
    }

    @Test
    void testAngleBetween_90() {
        final Vector2D vecA = new Vector2D(1.0, 2.0);
        final Vector2D vecB = new Vector2D(-2.0, 1.0);

        assertEquals(Math.PI * 0.5, vecA.angleBetween(vecB), 1e-10);
        assertEquals(Math.PI * 0.5, vecB.angleBetween(vecA), 1e-10);
    }

    @Test
    void testAngleBetween_25() {
        final Vector2D vecA = new Vector2D(Math.cos(Math.toRadians(60)), Math.sin(Math.toRadians(60)));
        final Vector2D vecB = new Vector2D(Math.cos(Math.toRadians(85)), Math.sin(Math.toRadians(85)));

        assertEquals(Math.toRadians(25), vecA.angleBetween(vecB), 1e-10);
        assertEquals(Math.toRadians(25), vecB.angleBetween(vecA), 1e-10);
    }

    @Test
    void testAngle() {
        final Vector2D vecA = new Vector2D(Math.cos(Math.toRadians(72)), Math.sin(Math.toRadians(72)));
        assertEquals(Math.toRadians(72), vecA.angle(), 1e-10);

        final Vector2D vecB = new Vector2D(Math.cos(Math.toRadians(-72)), Math.sin(Math.toRadians(-72)));
        assertEquals(Math.toRadians(-72), vecB.angle(), 1e-10);
    }

    @Test
    void testTurn90DegreeClockwise() {
        final Vector2D vecA = new Vector2D(1.0, 2.0);

        final Vector2D resA = vecA.turn90DegreeClockwise();
        final Vector2D resB = resA.turn90DegreeClockwise();
        final Vector2D resC = resB.turn90DegreeClockwise();
        final Vector2D resD = resC.turn90DegreeClockwise();

        assertEquals(2.0, resA.getX(), 1e-10);
        assertEquals(-1.0, resA.getY(), 1e-10);

        assertEquals(-1.0, resB.getX(), 1e-10);
        assertEquals(-2.0, resB.getY(), 1e-10);

        assertEquals(-2.0, resC.getX(), 1e-10);
        assertEquals(1.0, resC.getY(), 1e-10);

        assertEquals(1.0, resD.getX(), 1e-10);
        assertEquals(2.0, resD.getY(), 1e-10);
    }

    @Test
    void testTurn90DegreeCounterClockwise() {
        final Vector2D vecA = new Vector2D(1.0, 2.0);

        final Vector2D resA = vecA.turn90DegreeCounterClockwise();
        final Vector2D resB = resA.turn90DegreeCounterClockwise();
        final Vector2D resC = resB.turn90DegreeCounterClockwise();
        final Vector2D resD = resC.turn90DegreeCounterClockwise();

        assertEquals(-2.0, resA.getX(), 1e-10);
        assertEquals(1.0, resA.getY(), 1e-10);

        assertEquals(-1.0, resB.getX(), 1e-10);
        assertEquals(-2.0, resB.getY(), 1e-10);

        assertEquals(2.0, resC.getX(), 1e-10);
        assertEquals(-1.0, resC.getY(), 1e-10);

        assertEquals(1.0, resD.getX(), 1e-10);
        assertEquals(2.0, resD.getY(), 1e-10);
    }

    @Test
    void testLerp() {
        final Vector2D vecA = new Vector2D(3.0, 7.0);
        Vector2D resA = vecA.lerp(0.5);
        Vector2D resB = vecA.lerp(1.5);

        assertEquals(1.5, resA.getX(), 1e-10);
        assertEquals(3.5, resA.getY(), 1e-10);

        assertEquals(4.5, resB.getX(), 1e-10);
        assertEquals(10.5, resB.getY(), 1e-10);
    }

    @Test
    void testToString() {
        final Vector2D vecA = new Vector2D(1.0, 2.0);
        assertEquals("(x=1.0 y=2.0)",vecA.toString());
    }

}
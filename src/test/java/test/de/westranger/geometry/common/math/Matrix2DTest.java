package test.de.westranger.geometry.common.math;

import de.westranger.geometry.common.math.Matrix2D;
import de.westranger.geometry.common.math.Vector2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class Matrix2DTest {

    @Test
    void testConstructorParameterNull() {
        final double[][] data = null;
        assertThrows(IllegalArgumentException.class, () -> new Matrix2D(data));
    }

    @Test
    void testConstructorWrongParameterWrongSize() {
        final double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertThrows(IllegalArgumentException.class, () -> new Matrix2D(data));
    }

    @Test
    void testConstructorWrongParameterWrongLineSize() {
        final double[][] data = {{1, 1}, {1, 2, 3}};
        assertThrows(IllegalArgumentException.class, () -> new Matrix2D(data));
    }

    @Test
    void getValue() {
        final double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        final Matrix2D matrix = new Matrix2D(data);

        assertEquals(data[0][0], matrix.getValue(0, 0), 1e-10);
        assertEquals(data[0][1], matrix.getValue(0, 1), 1e-10);
        assertEquals(data[1][0], matrix.getValue(1, 0), 1e-10);
        assertEquals(data[1][1], matrix.getValue(1, 1), 1e-10);
    }

    @Test
    void testMultiplyScalar() {
        final Matrix2D matrix = new Matrix2D(1.0, 2.0, 3.0, 4.0);
        final Matrix2D result = matrix.multiply(2.0);

        assertEquals(2.0, result.getValue(0, 0), 1e-10);
        assertEquals(4.0, result.getValue(0, 1), 1e-10);
        assertEquals(6.0, result.getValue(1, 0), 1e-10);
        assertEquals(8.0, result.getValue(1, 1), 1e-10);
    }

    @Test
    void testMultiplyMatrix() {
        final Matrix2D matrixA = new Matrix2D(1.0, 2.0, 4.0, 5.0);
        final Matrix2D matrixB = new Matrix2D(7.0, 8.0, 9.0, 10.0);
        final Matrix2D result = matrixA.multiply(matrixB);

        assertEquals(25.0, result.getValue(0, 0), 1e-10);
        assertEquals(28.0, result.getValue(0, 1), 1e-10);
        assertEquals(73.0, result.getValue(1, 0), 1e-10);
        assertEquals(82.0, result.getValue(1, 1), 1e-10);
    }

    @Test
    void testMultiplyVector() {
        final Matrix2D mat = new Matrix2D(1.0, 2.0, 4.0, 5.0);
        final Vector2D vec = new Vector2D(7.0, 9.0);
        final Vector2D result = mat.multiply(vec);

        assertEquals(25.0, result.getX(), 1e-10);
        assertEquals(73.0, result.getY(), 1e-10);
    }

    @Test
    void testGetInverseA() {
        final Matrix2D matrix = new Matrix2D(2.0, -3.0, 1.0, 5.0);
        final Matrix2D result = matrix.getInverse();

        assertEquals(5.0 / 13.0, result.getValue(0, 0), 1e-10);
        assertEquals(3.0 / 13.0, result.getValue(0, 1), 1e-10);
        assertEquals(-1.0 / 13.0, result.getValue(1, 0), 1e-10);
        assertEquals(2.0 / 13.0, result.getValue(1, 1), 1e-10);
    }

    @Test
    void testGetInverseNotPossible() {
        final double[][] data = {{2, 3}, {2, 3}};
        assertThrows(ArithmeticException.class, () -> {
            Matrix2D mat = new Matrix2D(data);
            mat.getInverse();
        });
    }

    @Test
    void testGetInverseB() {
        final Matrix2D matrix = new Matrix2D(4.0, 7.0, 2.0, 6.0);
        final Matrix2D result = matrix.getInverse();

        assertEquals(0.6, result.getValue(0, 0), 1e-10);
        assertEquals(-0.7, result.getValue(0, 1), 1e-10);
        assertEquals(-0.2, result.getValue(1, 0), 1e-10);
        assertEquals(0.4, result.getValue(1, 1), 1e-10);
    }

    @Test
    void testGetTransposeA() {
        final Matrix2D matrix = new Matrix2D(2.0, -3.0, 1.0, 5.0);
        final Matrix2D result = matrix.transpose();

        assertEquals(2.0, result.getValue(0, 0), 1e-10);
        assertEquals(1.0, result.getValue(0, 1), 1e-10);
        assertEquals(-3.0, result.getValue(1, 0), 1e-10);
        assertEquals(5.0, result.getValue(1, 1), 1e-10);
    }

    @Test
    void testGetTransposeB() {
        final Matrix2D matrix = new Matrix2D(1.0, 2.0, 3.0, 4.0);
        final Matrix2D result = matrix.transpose();

        assertEquals(1.0, result.getValue(0, 0), 1e-10);
        assertEquals(3.0, result.getValue(0, 1), 1e-10);
        assertEquals(2.0, result.getValue(1, 0), 1e-10);
        assertEquals(4.0, result.getValue(1, 1), 1e-10);
    }
}
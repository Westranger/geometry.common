package test.de.westranger.geometry.common;

import de.westranger.geometry.common.Matrix2D;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Matrix2DTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void constructorParameterNull() {
        final double[][] data = null;
        assertThrows(IllegalArgumentException.class, () -> new Matrix2D(data));
    }

    @Test
    void constructorWrongParameterWrongSize() {
        final double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertThrows(IllegalArgumentException.class, () -> new Matrix2D(data));
    }

    @Test
    void constructorWrongParameterWrongLineSize() {
        final double[][] data = {{1, 1}, {1, 2, 3}};
        assertThrows(IllegalArgumentException.class, () -> new Matrix2D(data));
    }

    @Test
    void getValue() {
        final double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        final Matrix2D matrix = new Matrix2D(data);

        assertEquals(data[0][0], matrix.getValue(0, 0), 1e-3);
        assertEquals(data[0][1], matrix.getValue(0, 1), 1e-3);
        assertEquals(data[1][0], matrix.getValue(1, 0), 1e-3);
        assertEquals(data[1][1], matrix.getValue(1, 1), 1e-3);
    }

    @Test
    void multiply() {
        final double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        final Matrix2D matrix = new Matrix2D(data);
        final Matrix2D result = matrix.multiply(2.0);

        assertEquals(2.0, result.getValue(0, 0), 1e-3);
        assertEquals(4.0, result.getValue(0, 1), 1e-3);
        assertEquals(6.0, result.getValue(1, 0), 1e-3);
        assertEquals(8.0, result.getValue(1, 1), 1e-3);
    }

    @Test
    void getInverse() {
        final double[][] data = {{2.0, -3.0}, {1.0, 5.0}};
        final Matrix2D matrix = new Matrix2D(data);
        final Matrix2D result = matrix.getInverse();

        assertEquals(5.0 / 13.0, result.getValue(0, 0), 1e-3);
        assertEquals(3.0 / 13.0, result.getValue(0, 1), 1e-3);
        assertEquals(-1.0 / 13.0, result.getValue(1, 0), 1e-3);
        assertEquals(2.0 / 13.0, result.getValue(1, 1), 1e-3);
    }

    @Test
    void getTranspose() {
        final double[][] data = {{2.0, -3.0}, {1.0, 5.0}};
        final Matrix2D matrix = new Matrix2D(data);
        final Matrix2D result = matrix.transpose();

        assertEquals(2.0, result.getValue(0, 0), 1e-3);
        assertEquals(1.0, result.getValue(0, 1), 1e-3);
        assertEquals(-3.0, result.getValue(1, 0), 1e-3);
        assertEquals(5.0, result.getValue(1, 1), 1e-3);
    }

}
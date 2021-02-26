package test.de.westranger.geometry.common;

import de.westranger.geometry.common.math.Rotation2D;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Rotation2DTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSmallestAngle() {
        Rotation2D rot = new Rotation2D(Math.toRadians(45));
        assertEquals(Math.toRadians(45), rot.getSmallestAngle(),1e-6);
    }

    @Test
    void getInverse() {
        Rotation2D rot = new Rotation2D(Math.toRadians(45));
        assertEquals(Math.toRadians(135), rot.getInverse().getSmallestAngle(),1e-6);
    }

    @Test
    void multiply() {
    }

    @Test
    void testMultiply() {
    }
}
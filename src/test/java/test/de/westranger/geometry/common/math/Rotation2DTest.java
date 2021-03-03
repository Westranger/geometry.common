package test.de.westranger.geometry.common.math;

import de.westranger.geometry.common.math.Rotation2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Rotation2DTest {

    @Test
    void testGetSmallestAngle() {
        Rotation2D rot = new Rotation2D(Math.toRadians(45));
        assertEquals(Math.toRadians(45), rot.getSmallestAngle(), 1e-10);
    }

    @Test
    void testGetInverse() {
        Rotation2D rot = new Rotation2D(Math.toRadians(45));
        assertEquals(Math.toRadians(135), rot.getInverse().getSmallestAngle(), 1e-10);
    }

}
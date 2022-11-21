package test.de.westranger.geometry.common.math;

import de.westranger.geometry.common.math.Rotation2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Rotation2DTest {

    @Test
    void testGetSmallestAngle() {
        Rotation2D rot = new Rotation2D(Math.toRadians(45));
        assertEquals(Math.toRadians(45), rot.getSmallestAngle(), 1e-10);

        rot = new Rotation2D(Math.toRadians(135));
        assertEquals(Math.toRadians(135), rot.getSmallestAngle(), 1e-10);

        rot = new Rotation2D(Math.toRadians(215));
        assertEquals(Math.toRadians(215 - 360), rot.getSmallestAngle(), 1e-10);

        rot = new Rotation2D(Math.toRadians(305));
        assertEquals(Math.toRadians(305 - 360), rot.getSmallestAngle(), 1e-10);

        rot = new Rotation2D(Math.toRadians(-30));
        assertEquals(Math.toRadians(-30), rot.getSmallestAngle(), 1e-10);
    }

    @Test
    void testIdentity() {
        Rotation2D rotA = new Rotation2D(Math.toRadians(45));
        Rotation2D rotB = new Rotation2D(rotA.transpose());
        Rotation2D rotC = new Rotation2D(rotA.multiply(rotB));
        assertEquals(1, rotC.getValue(0, 0), 1e-10);
        assertEquals(1, rotC.getValue(1, 1), 1e-10);
        assertEquals(0, rotC.getValue(1, 0), 1e-10);
        assertEquals(0, rotC.getValue(0, 1), 1e-10);
    }

    @Test
    void testGetInverseA() {
        Rotation2D rot = new Rotation2D(Math.toRadians(45));
        assertEquals(Math.toRadians(-45), rot.getInverse().getSmallestAngle(), 1e-10);
    }

    @Test
    void testGetInverseB() {
        Rotation2D rot = new Rotation2D(Math.toRadians(135));
        assertEquals(Math.toRadians(-135), rot.getInverse().getSmallestAngle(), 1e-10);
    }

    @Test
    void testGetAngularDifferenceA() {
        Rotation2D rotA = new Rotation2D(Math.toRadians(30));
        Rotation2D rotB = new Rotation2D(Math.toRadians(25));
        double x = Math.toDegrees(rotA.angularDifference(rotB).getSmallestAngle());

        assertEquals(Math.toRadians(5), rotA.angularDifference(rotB).getSmallestAngle(), 1e-10);
    }

    @Test
    void testGetAngularDifferenceB() {
        Rotation2D rotA = new Rotation2D(Math.toRadians(30));
        Rotation2D rotB = new Rotation2D(Math.toRadians(-25));
        double x = Math.toDegrees(rotA.angularDifference(rotB).getSmallestAngle());

        assertEquals(Math.toRadians(55), rotA.angularDifference(rotB).getSmallestAngle(), 1e-10);
    }

    @Test
    void testGetAngularDifferenceC() {
        Rotation2D rotA = new Rotation2D(Math.toRadians(30));
        Rotation2D rotB = new Rotation2D(Math.toRadians(190));
        double x = Math.toDegrees(rotA.angularDifference(rotB).getSmallestAngle());

        assertEquals(Math.toRadians(160), rotA.angularDifference(rotB).getSmallestAngle(), 1e-10);
    }

    @Test
    void testGetAngularDifferenceD() {
        Rotation2D rotA = new Rotation2D(Math.toRadians(30));
        Rotation2D rotB = new Rotation2D(Math.toRadians(270));
        double x = Math.toDegrees(rotA.angularDifference(rotB).getSmallestAngle());

        assertEquals(Math.toRadians(120), rotA.angularDifference(rotB).getSmallestAngle(), 1e-10);
    }

    @Test
    void testGetAngularDifferenceE() {
        Rotation2D rotA = new Rotation2D(Math.toRadians(30));
        Rotation2D rotB = new Rotation2D(Math.toRadians(27));
        double x = Math.toDegrees(rotA.angularDifference(rotB).getSmallestAngle());

        assertEquals(Math.toRadians(3), rotA.angularDifference(rotB).getSmallestAngle(), 1e-10);
    }

}
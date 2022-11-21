package test.de.westranger.geometry.common.complex.impl;

import de.westranger.geometry.common.complex.impl.PolygonPoint2D;
import de.westranger.geometry.common.simple.Point2D;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PolygonPoint2DTest {

    @Test
    void areaSignedClockwise() {
        PolygonPoint2D poly = new PolygonPoint2D();

        poly.add(new Point2D(1.0, 1.0));
        poly.add(new Point2D(4.0, 1.0));
        poly.add(new Point2D(4.0, 3.0));
        poly.add(new Point2D(3.0, 3.0));
        poly.add(new Point2D(3.0, 2.0));
        poly.add(new Point2D(2.0, 2.0));
        poly.add(new Point2D(2.0, 4.0));
        poly.add(new Point2D(1.0, 4.0));

        assertEquals(3.0 + 1.0 + 2.0, poly.areaSigned(), 1e-10);
    }

    @Test
    void areaSignedCounterClockwise() {
        PolygonPoint2D poly = new PolygonPoint2D();

        poly.add(new Point2D(1.0, 1.0));
        poly.add(new Point2D(4.0, 1.0));
        poly.add(new Point2D(4.0, 3.0));
        poly.add(new Point2D(3.0, 3.0));
        poly.add(new Point2D(3.0, 2.0));
        poly.add(new Point2D(2.0, 2.0));
        poly.add(new Point2D(2.0, 4.0));
        poly.add(new Point2D(1.0, 4.0));

        Collections.reverse(poly);
        assertEquals(-(3.0 + 1.0 + 2.0), poly.areaSigned(), 1e-10);
    }

    @Test
    void areaSignedSizeZero() {
        PolygonPoint2D poly = new PolygonPoint2D();

        poly.add(new Point2D(1.0, 1.0));
        poly.add(new Point2D(1.0, 2.0));
        poly.add(new Point2D(1.0, 3.0));
        poly.add(new Point2D(1.0, 2.0));
        assertEquals(0.0, poly.areaSigned(), 1e-10);
    }
}
package de.westranger.geometry.common.complex;

import de.westranger.geometry.common.math.Vector2D;
import de.westranger.geometry.common.simple.ArcCircle;
import de.westranger.geometry.common.simple.Point2D;

public final class CircleSegment extends Segment {
    private final Vector2D direction_center;
    private final Vector2D direction_p_minus;
    private final Vector2D direction_p_plus;
    private final double alpha;
    private final Point2D origin;
    private ArcCircle centerCircle;

    public CircleSegment(final Point2D p0, final Point2D p1, final Point2D p2, final double radius) {
        origin = p1;
        direction_p_minus = p1.diff(p0);
        direction_p_plus = p1.diff(p2);

        direction_p_minus.normalize();
        direction_p_plus.normalize();

        // Richtung der Winkelhalbierenden
        direction_center = direction_p_minus.add(direction_p_plus).multiply(0.5);
        direction_center.normalize();

        // alpha des Dreiecks
        alpha = direction_center.angleBetween(direction_p_minus);
        // angle_half = angle_half * 180.0 / M_PI;
        setRadiusAndMargin(radius);
    }

    void setRadiusAndMargin(final double radius) {
        final double hyp = radius / Math.sin(alpha);
        final double ankh = Math.sqrt(hyp * hyp - radius * radius);

        final Vector2D m_margin_d_plus_center = direction_p_plus.multiply(ankh);
        final Vector2D m_margin_d_minus_center = direction_p_minus.multiply(ankh);
        final Vector2D circle_center = direction_center.multiply(hyp);
        final Vector2D angleVecStart = m_margin_d_minus_center.subtract(circle_center);
        final Vector2D angleVecEnd = m_margin_d_plus_center.subtract(circle_center);

        final double arcStart = Math.atan2(angleVecStart.getY(), angleVecStart.getX());
        double arcLen = Math.atan2(angleVecEnd.getY(), angleVecEnd.getX()) - Math.atan2(angleVecStart.getY(), angleVecStart.getX());

        if (arcLen > Math.PI) {
            arcLen = arcLen - 2 * Math.PI;
        } else if (arcLen < -Math.PI) {
            arcLen = arcLen + 2 * Math.PI;
        }

        centerCircle = new ArcCircle(origin.pointAt(circle_center), origin.pointAt(m_margin_d_minus_center), origin.pointAt(m_margin_d_plus_center), arcStart, arcLen, radius);
    }

    public ArcCircle getCenterCircle() {
        return centerCircle;
    }
}

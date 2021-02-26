package de.westranger.geometry.common.complex;

import de.westranger.geometry.common.math.Vector2D;
import de.westranger.geometry.common.simple.Circle;

public final class CircleSegment extends Segment {
    private final Vector2D direction_center;
    private final Vector2D direction_p_minus;
    private final Vector2D direction_p_plus;
    private final double alpha;
    private final Vector2D origin;
    private Circle upperCircle;
    private Circle centerCircle;
    private Circle lowerCircle;

    public CircleSegment(final Vector2D p0, final Vector2D p1, final Vector2D p2, final double radius, final double margin) {
        origin = p1;
        direction_p_minus = p0.subtract(p1);
        direction_p_plus = p2.subtract(p1);

        direction_p_minus.normalize();
        direction_p_plus.normalize();

        // Richtung der Winkelhalbierenden
        direction_center = direction_p_minus.add(direction_p_plus).multiply(0.5);
        direction_center.normalize();

        // alpha des Dreiecks
        alpha = direction_center.angleBetween(direction_p_minus);
        // angle_half = angle_half * 180.0 / M_PI;
        setRadiusAndMargin(radius, margin);
    }

    void setRadiusAndMargin(final double radius, final double margin) {
        final double hyp = radius / Math.sin(alpha);
        final double ankh = Math.sqrt(hyp * hyp - radius * radius);

        final Vector2D m_margin_d_plus_center = direction_p_plus.multiply(ankh);
        final Vector2D m_margin_d_plus_right = m_margin_d_plus_center.add(direction_p_plus.turn90DegreeClockwise().multiply(margin));
        final Vector2D m_margin_d_plus_left = m_margin_d_plus_center.add(direction_p_plus.turn90DegreeCounterClockwise().multiply(margin));

        final Vector2D m_margin_d_minus_center = direction_p_minus.multiply(ankh);
        final Vector2D m_margin_d_minus_right = m_margin_d_minus_center.add(direction_p_minus.turn90DegreeClockwise().multiply(margin));
        final Vector2D m_margin_d_minus_left = m_margin_d_minus_center.add(direction_p_minus.turn90DegreeCounterClockwise().multiply(margin));

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

        centerCircle = new Circle(origin.add(circle_center), origin.add(m_margin_d_minus_center), origin.add(m_margin_d_plus_center), arcStart, arcLen, radius);
        upperCircle = new Circle(origin.add(circle_center), origin.add(m_margin_d_minus_left), origin.add(m_margin_d_plus_right), arcStart, arcLen, radius + margin);
        lowerCircle = new Circle(origin.add(circle_center), origin.add(m_margin_d_minus_right), origin.add(m_margin_d_plus_left), arcStart, arcLen, radius - margin);
    }

    public Circle getUpperCircle() {
        return upperCircle;
    }

    public Circle getCenterCircle() {
        return centerCircle;
    }

    public Circle getLowerCircle() {
        return lowerCircle;
    }
}

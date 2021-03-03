package de.westranger.geometry.common.simple;

public class ArcCircle extends Circle {

    private final Point2D arcStart;
    private final Point2D arcEnd;
    private final double arcStartAngle;
    private final double arcLength;

    public ArcCircle(final Point2D center, final Point2D arcStart, final Point2D arcEnd, final double arcStartAngle, final double arcLength, final double radius) {
        super(center, radius);
        this.arcStart = arcStart;
        this.arcEnd = arcEnd;
        this.arcStartAngle = arcStartAngle;
        this.arcLength = arcLength;
    }

    public Point2D getArcStart() {
        return arcStart;
    }

    public Point2D getArcEnd() {
        return arcEnd;
    }

    public double getArcStartAngle() {
        return arcStartAngle;
    }

    public double getArcLength() {
        return arcLength;
    }
}

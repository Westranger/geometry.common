package de.westranger.geometry.common;

public final class Circle extends Geometry{
    private final Vector2D center;
    private final Vector2D arcStart;
    private final Vector2D arcEnd;
    private final double arcStartAngle;
    private final double arcLength;
    private final double radius;

    public Circle(final Vector2D center, final Vector2D arcStart, final Vector2D arcEnd, final double arcStartAngle, final double arcLength, final double radius) {
        this.center = center;
        this.arcStart = arcStart;
        this.arcEnd = arcEnd;
        this.arcStartAngle = arcStartAngle;
        this.arcLength = arcLength;
        this.radius = radius;
    }

    public Vector2D getCenter() {
        return center;
    }

    public Vector2D getArcStart() {
        return arcStart;
    }

    public Vector2D getArcEnd() {
        return arcEnd;
    }

    public double getArcStartAngle() {
        return arcStartAngle;
    }

    public double getArcLength() {
        return arcLength;
    }

    public double getRadius() {
        return radius;
    }
}

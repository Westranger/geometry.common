package de.westranger.geometry.common.simple;

public class Ellipse {
    protected final double radiusX;
    protected final double radiusY;

    public Ellipse(final double radiusX, final double radiusY) {
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    public double getRadiusX() {
        return radiusX;
    }

    public double getRadiusY() {
        return radiusY;
    }
}

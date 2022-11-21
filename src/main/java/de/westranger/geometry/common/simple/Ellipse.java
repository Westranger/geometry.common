package de.westranger.geometry.common.simple;

public class Ellipse extends Geometry{
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

    @Override
    public BoundingBox getBoundingBox() {
        // TODO NYI
        return null;
    }

    //https://math.stackexchange.com/questions/1688449/intersection-of-two-ellipses
}

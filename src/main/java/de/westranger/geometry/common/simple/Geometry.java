package de.westranger.geometry.common.simple;

public abstract class Geometry {

    public abstract BoundingBox getBoundingBox();

    // TODO rotate around point for every geometry

    void checkValueValidity(final double value) {
        if (Double.isInfinite(value)) {
            throw new IllegalArgumentException("value must not be infinite value=" + value);
        }

        if (Double.isNaN(value)) {
            throw new IllegalArgumentException("value must not be NaN");
        }
    }

}

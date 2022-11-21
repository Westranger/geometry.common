package de.westranger.geometry.common.simple;

import de.westranger.geometry.common.math.Rotation2D;

public final class ArcEllipse extends Ellipse {
    private final Rotation2D rot;

    public ArcEllipse(double radiusX, double radiusY, final Rotation2D rot) {
        super(radiusX, radiusY);
        this.rot = rot;
    }

    public Rotation2D getRotation() {
        return this.rot;
    }

    @Override
    public BoundingBox getBoundingBox() {
        // TODO NYI
        return null;
    }
}

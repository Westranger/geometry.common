package de.westranger.geometry.common.simple.axis.aligned;

import de.westranger.geometry.common.math.Rotation2D;

public enum Orientation {
    Degree90(Math.PI * 0.5),
    Degree0(0.0),
    Degree180(Math.PI),
    Degree275(Math.PI * 1.5);

    private final Rotation2D value;

    // private enum constructor
    private Orientation(final double value) {
        this.value = new Rotation2D(value);
    }

    public Rotation2D getRotation() {
        return this.value;
    }

}

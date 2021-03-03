package de.westranger.geometry.common.math;

public final class Rotation2D extends Matrix2D {

    // https://www.continuummechanics.org/rotationmatrix.html

    public Rotation2D(final double radiant) {
        super(Math.cos(radiant), -Math.sin(radiant), Math.sin(radiant), Math.cos(radiant));
    }

    public Rotation2D(final Matrix2D mat) {
        super(mat.getValue(0, 0), mat.getValue(0, 1), mat.getValue(1, 0), mat.getValue(1, 1));
    }

    public double getSmallestAngle() {
        return Math.atan2(this.data[0][0], this.data[1][0]);
    }

    public Rotation2D getInverse() {
        return new Rotation2D(super.transpose());
    }

}

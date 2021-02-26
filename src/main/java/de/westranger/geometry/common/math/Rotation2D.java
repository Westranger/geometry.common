package de.westranger.geometry.common.math;

public final class Rotation2D {

    // https://www.continuummechanics.org/rotationmatrix.html

    private final Matrix2D rotation;

    public Rotation2D(final double radiant) {
        final double[][] data = {{Math.cos(radiant), -Math.sin(radiant)}, {Math.sin(radiant), Math.cos(radiant)}};
        this.rotation = new Matrix2D(data);
    }

    public Rotation2D(final Matrix2D mat) {
        final double[][] data = {{mat.getValue(0, 0), mat.getValue(0, 1)}, {mat.getValue(1, 0), mat.getValue(1, 1)}};
        this.rotation = new Matrix2D(data);
    }

    public double getSmallestAngle() {
        return Math.atan2(this.rotation.getValue(0, 0), this.rotation.getValue(1, 0));
    }

    public Rotation2D getInverse() {
        return new Rotation2D(this.rotation.transpose());
    }

    public Matrix2D multiply(final Matrix2D matrix) {
        return this.rotation.multiply(matrix);
    }

    public Vector2D multiply(final Vector2D vector) {
        return this.rotation.multiply(vector);
    }

}

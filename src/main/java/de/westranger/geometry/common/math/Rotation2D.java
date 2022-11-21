package de.westranger.geometry.common.math;

public final class Rotation2D extends Matrix2D {

    /**
     * @param radiant
     * @see <a href="https://www.continuummechanics.org/rotationmatrix.html">Rotaion Matrix</a>
     */

    public Rotation2D(final double radiant) {
        super(Math.cos(radiant), -Math.sin(radiant), Math.sin(radiant), Math.cos(radiant));
    }

    public Rotation2D(final Matrix2D mat) {
        super(mat.getValue(0, 0), mat.getValue(0, 1), mat.getValue(1, 0), mat.getValue(1, 1));
    }

    public double getSmallestAngle() {
        return Math.atan2(this.data[1][0], this.data[1][1]);
    }

    public Rotation2D getInverse() {
        return new Rotation2D(super.transpose());
    }

    public Rotation2D angularDifference(final Rotation2D rot) {
        final Vector2D flatA = new Vector2D(this.data[0][0], this.data[1][0]);
        final Vector2D flatB = new Vector2D(rot.getValue(0, 0), rot.getValue(1, 0));
        return new Rotation2D(flatA.angleBetween(flatB));
    }

    public Rotation2D addAngle(final Rotation2D rot) {
        return new Rotation2D(this.getInverse().multiply(rot));
    }

}

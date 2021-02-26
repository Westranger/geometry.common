package de.westranger.geometry.common.math;

public final class Vector2D {
    private double x;
    private double y;

    public Vector2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Vector2D add(final Vector2D vec) {
        return new Vector2D(this.x + vec.getX(), this.y + vec.getY());
    }

    public Vector2D subtract(final Vector2D vec) {
        return new Vector2D(this.x - vec.getX(), this.y - vec.getY());
    }

    public Vector2D multiply(final double value) {
        return new Vector2D(this.x * value, this.y * value);
    }

    public double norm() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public double dot(final Vector2D vec) {
        return this.x * vec.getX() + this.y * vec.getY();
    }

    public Vector2D normalize() {
        final double len = this.norm();
        this.x /= len;
        this.y /= len;
        return this;
    }

    public double cross(final Vector2D vec) {
        return this.x * vec.getY() - this.y * vec.getX();
    }

    public double angleBetween(final Vector2D vec) {
        return Math.acos(this.dot(vec) / (this.norm() * vec.norm()));
    }

    public double angle() {
        return Math.atan2(this.y, this.x);
    }

    public Vector2D turn90DegreeClockwise() {
        return new Vector2D(this.y, -this.x);
    }

    public Vector2D turn90DegreeCounterClockwise() {
        return new Vector2D(-this.y, this.x);
    }

    public Vector2D lerp(final double t) {
        return this.multiply(t);
    }

    @Override
    public String toString() {
        return "(x=" + x + " y=" + y + ")";
    }
}
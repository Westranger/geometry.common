package de.westranger.geometry.common.simple;

public final class BoundingBox {
    final Point2D min;
    final Point2D max;

    public BoundingBox(final Point2D min, final Point2D max) {
        if (min == null) {
            throw new IllegalArgumentException("min point must not be null");
        }

        if (max == null) {
            throw new IllegalArgumentException("max point must not be null");
        }

        this.min = min;
        this.max = max;
    }

    public BoundingBox merge(final BoundingBox box) {
        if (box == null) {
            throw new IllegalArgumentException("BoundingBox must not be null");
        }

        final double minX = Math.min(this.min.getX(), box.getMin().getX());
        final double minY = Math.min(this.min.getY(), box.getMin().getY());
        final double maxX = Math.max(this.max.getX(), box.getMax().getX());
        final double maxY = Math.max(this.max.getY(), box.getMax().getY());

        final Point2D newMin = new Point2D(minX, minY);
        final Point2D newMax = new Point2D(maxX, maxY);
        return new BoundingBox(newMin, newMax);
    }

    public Point2D getMin() {
        return min;
    }

    public Point2D getMax() {
        return max;
    }

    public boolean isInfinite() {
        return Double.isInfinite(this.min.getX()) || Double.isInfinite(this.min.getY()) || Double.isInfinite(this.max.getX()) || Double.isInfinite(this.max.getY());
    }
}

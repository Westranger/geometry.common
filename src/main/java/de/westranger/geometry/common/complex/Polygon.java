package de.westranger.geometry.common.complex;

import de.westranger.geometry.common.simple.Point2D;

public abstract class Polygon {
    public abstract Polygon intersection(final Polygon poly);

    public abstract Polygon union(final Polygon poly);

    public abstract Polygon buffer(final double thickness);

    public abstract Polygon rotate(final Point2D rotationPoint, final double angle);

    public abstract boolean isWithin(final Point2D point);
}

package de.westranger.geometry.common.complex.impl;

import de.westranger.geometry.common.simple.Point2D;
import de.westranger.geometry.common.complex.Polygon;

public final class PolygonComplex extends PolygonPoint2D {
    // made up of circle arcs, elliptical arcs and lines
    // are intersections / unions  of circles / ellipses only handled here or within the circle/ellipses class?

    @Override
    public Polygon intersection(Polygon poly) {
        return null;
    }

    @Override
    public Polygon union(Polygon poly) {
        return null;
    }

    @Override
    public Polygon buffer(double thickness) {
        return null;
    }

    @Override
    public Polygon rotate(Point2D rotationPoint, double angle) {
        return null;
    }

    @Override
    public boolean isWithin(Point2D point) {
        return false;
    }

    @Override
    public double areaSigned() {
        return 0;
    }

}

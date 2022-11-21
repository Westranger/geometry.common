package de.westranger.geometry.common.complex.impl;

import de.westranger.geometry.common.complex.Polygon;
import de.westranger.geometry.common.operation.GeometryOperation;
import de.westranger.geometry.common.operation.result.GeometryResult;
import de.westranger.geometry.common.simple.Point2D;
import de.westranger.geometry.common.simple.Segment;

import java.util.LinkedList;

public class PolygonPoint2D extends LinkedList<Point2D> implements Polygon {

    // TODO Polygon darf sich nicht selbst intersecten
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

    // TODO testcase schreiben, bei dem die unendlich linie genau durch zwei aufeinanderfolgende Punkte des Polygon trifft, sprich die Schnittmenge der Segmente ist wieder ein Segment
    // TODO testcase für leeres Polygon
    @Override
    public boolean isWithin(Point2D point) {
        if (this.isEmpty()) {
            return false;
        }

        final Segment seg = new Segment(point, new Point2D(Double.POSITIVE_INFINITY, point.getY()));
        int cnt = 0;
        Segment cut = new Segment(this.getLast(), this.getFirst());
        GeometryResult<Segment> result = GeometryOperation.intersection(seg, cut);

        if (result.getPoints().isPresent() || result.getGeometry().isPresent()) {
            cnt++;
        }

        for (int i = 1; i < this.size(); i++) {
            cut = new Segment(this.get(i - 1), this.get(i));
            result = GeometryOperation.intersection(seg, cut);
            if (result.getPoints().isPresent() || result.getGeometry().isPresent()) {
                cnt++;
            }
        }

        return cnt % 2 == 0;
    }

    /**
     * @see <a href="https://mathworld.wolfram.com/PolygonArea.html">http://google.com</a>
     */
    @Override
    public double areaSigned() {
        if (this.size() < 3) {
            return 0.0;
        }

        Point2D pt1 = this.getLast();
        Point2D pt2 = this.getFirst();
        double sum = pt1.getX() * pt2.getY() - pt2.getX() * pt1.getY();

        for (int i = 1; i < this.size(); i++) {
            pt1 = this.get(i - 1);
            pt2 = this.get(i);
            sum += pt1.getX() * pt2.getY() - pt2.getX() * pt1.getY();
        }

        return sum * 0.5;
    }
}

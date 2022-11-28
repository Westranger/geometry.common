package de.westranger.geometry.common.complex.impl;

import de.westranger.geometry.common.complex.Polygon;
import de.westranger.geometry.common.math.Vector2D;
import de.westranger.geometry.common.operation.GeometryOperation;
import de.westranger.geometry.common.operation.result.GeometryResult;
import de.westranger.geometry.common.simple.Point2D;
import de.westranger.geometry.common.simple.Segment;

import java.util.Iterator;
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
        if (this.size() < 3) {
            return false;
        }

        final Segment seg = new Segment(point, new Point2D(Double.POSITIVE_INFINITY, point.getY()));
        int cnt = 0;
        Segment cut = new Segment(this.getLast(), this.getFirst());
        GeometryResult<Segment> result = GeometryOperation.intersection(seg, cut);

        if (result.getPoints().isPresent() || result.getGeometry().isPresent()) {
            cnt++;
        }

        // TODO auf iterator umstellen, wegen linked list
        for (int i = 1; i < this.size(); i++) {
            cut = new Segment(this.get(i - 1), this.get(i));
            result = GeometryOperation.intersection(seg, cut);
            if (result.getPoints().isPresent() || result.getGeometry().isPresent()) {
                cnt++;
            }
        }

        return cnt % 2 == 0;
    }

    @Override
    public int windingNumber(final Point2D pt) {
        if (this.size() < 3) {
            return 0;
        }

        final Iterator<Point2D> iter = this.iterator();
        Vector2D prevVec = pt.diff(iter.next());
        Vector2D currentVec;

        double rotSum = 0.0;
        while (iter.hasNext()) {
            currentVec = pt.diff(iter.next());
            rotSum += prevVec.angleBetweenSinged(currentVec);
            prevVec = currentVec;
        }

        currentVec = pt.diff(this.getFirst());
        rotSum += prevVec.angleBetweenSinged(currentVec);
        rotSum /= 2.0 * Math.PI;

        return (int) rotSum;
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

        final Iterator<Point2D> iter = this.iterator();
        pt1 = iter.next();

        while(iter.hasNext()){
            pt2 = iter.next();
            sum += pt1.getX() * pt2.getY() - pt2.getX() * pt1.getY();
            pt1 = pt2;
        }

        return sum * 0.5;
    }
}

package de.westranger.geometry.common.simple.axis.aligned;

import de.westranger.geometry.common.simple.BoundingBox;
import de.westranger.geometry.common.simple.Point2D;

public final class Rectangle {

    private Point2D position;
    private final double width;
    private final double height;
    private Orientation orientation;

    public Rectangle(final Point2D position, final double width, final double height, final Orientation orientation) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.orientation = orientation;
    }

    public Rectangle(final Point2D position, final double width, final double height) {
        this(position, width, height, Orientation.Degree0);
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(final Orientation orientation) {
        this.orientation = orientation;
    }

    public BoundingBox getBoundingBox() {
        return null;
    }

    public boolean intersects(final Rectangle rectangle) {
        final BoundingBox bbA = this.getBoundingBox();
        final BoundingBox bbB = rectangle.getBoundingBox();
        final boolean doesNotIntersect = bbA.getMin().getX() > bbB.getMax().getX() || bbA.getMax().getX() < bbB.getMin().getX() ||
                bbA.getMin().getY() > bbB.getMax().getY() || bbA.getMax().getY() < bbB.getMin().getY();
        return !doesNotIntersect;
    }
}

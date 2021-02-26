package de.westranger.geometry.common;

public final class LineSegment extends Segment{
    private final Line lineUpper;
    private final Line lineCenter;
    private final Line lineLower;

    public LineSegment(final Vector2D start, final Vector2D end, final double margin) {
        final Vector2D direction = end.subtract(start).normalize();

        lineCenter = new Line(start, end);
        lineUpper = new Line(start.add(direction.turn90DegreeClockwise().multiply(margin)), end.add(direction.turn90DegreeClockwise().multiply(margin)));
        lineLower = new Line(start.add(direction.turn90DegreeCounterClockwise().multiply(margin)), end.add(direction.turn90DegreeCounterClockwise().multiply(margin)));
    }

    public Line getLineUpper() {
        return lineUpper;
    }

    public Line getLineCenter() {
        return lineCenter;
    }

    public Line getLineLower() {
        return lineLower;
    }
}

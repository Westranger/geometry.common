package de.westranger.geometry.common.plot;

import de.westranger.geometry.common.simple.Point2D;
import de.westranger.geometry.common.complex.Polygon;
import de.westranger.geometry.common.complex.Segment;
import de.westranger.geometry.common.simple.Circle;
import de.westranger.geometry.common.simple.Line;


public final class SVGGenerator {

    private final double width;
    private final double height;

    public SVGGenerator(final double width, final double height) {

        this.width = width;
        this.height = height;
    }

    public void add(Point2D point, Colors color, final double radius) {

    }

    public void add(Line line, Colors color, final double width) {

    }

    public void add(final Circle circle, final Colors colorStroke, final Colors colorLine, final double widthStroke) {

    }

    public void add(Segment segment, final Colors color, final double width) {

    }

    public void add(Polygon poly, final Colors colorStroke, final Colors colorLine, final double widthStroke) {

    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}

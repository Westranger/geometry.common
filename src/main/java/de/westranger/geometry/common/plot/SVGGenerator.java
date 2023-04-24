package de.westranger.geometry.common.plot;

import de.westranger.geometry.common.complex.Polygon;
import de.westranger.geometry.common.simple.*;
import org.jfree.svg.MeetOrSlice;
import org.jfree.svg.SVGGraphics2D;
import org.jfree.svg.SVGUtils;
import org.jfree.svg.ViewBox;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public final class SVGGenerator {

    private final SVGGraphics2D svgGenerator;
    final static double SCALE_FACTOR = 100.0;
    BoundingBox viewBox;

    public SVGGenerator(final double width, final double height) {
        this.svgGenerator = new SVGGraphics2D(width, height);
        this.viewBox = new BoundingBox(new Point2D(0.0, 0.0), new Point2D(0.0, 0.0));
    }

    // TODO stroke witdh entfernen und die dick automatsich genereieren, ca 0,5% von der max(width, height)

    public void add(Point2D point, Colors color, final double radius) {
        final double ws = radius * 0.5;
        this.svgGenerator.setPaint(Color.decode(color.getColor()));
        this.svgGenerator.fillOval((int) ((point.getX() - ws) * SCALE_FACTOR), (int) ((point.getY() - ws) * SCALE_FACTOR), (int) (radius * SCALE_FACTOR), (int) (radius * SCALE_FACTOR));

        final BoundingBox bBox = new BoundingBox(point, point);
        if (!bBox.isInfinite()) {
            this.viewBox = this.viewBox.merge(bBox);
        }
    }

    public void add(Line line, Colors color, final double width) {
        // TODO NYI
    }

    public void add(final Circle circle, final Colors color, final double widthStroke) {
        this.svgGenerator.setPaint(Color.decode(color.getColor()));
        this.svgGenerator.drawOval((int) ((circle.getCenter().getX() - circle.getRadius()) * SCALE_FACTOR), (int) ((circle.getCenter().getY() - circle.getRadius()) * SCALE_FACTOR), (int) (circle.getRadius() * 2.0 * SCALE_FACTOR), (int) (circle.getRadius() * 2.0 * SCALE_FACTOR));
        this.add(circle.getCenter(), color, widthStroke);

        final BoundingBox bBox = circle.getBoundingBox();
        if (!bBox.isInfinite()) {
            this.viewBox = this.viewBox.merge(bBox);
        }
    }

    public void add(final Segment segment, final Colors color, final double widthStroke) {
        this.svgGenerator.setPaint(Color.decode(color.getColor()));
        this.svgGenerator.drawLine((int) (segment.getStart().getX() * SCALE_FACTOR), (int) (segment.getStart().getY() * SCALE_FACTOR), (int) (segment.getEnd().getX() * SCALE_FACTOR), (int) (segment.getEnd().getY() * SCALE_FACTOR));
        this.add(segment.getStart(), color, widthStroke);
        this.add(segment.getEnd(), color, widthStroke);

        final BoundingBox bBox = segment.getBoundingBox();
        if (!bBox.isInfinite()) {
            this.viewBox = this.viewBox.merge(bBox);
        }
    }

    public void add(Polygon poly, final Colors colorStroke, final Colors colorLine, final double widthStroke) {
        // TODO NYI
    }

    public void writeFile(final String filename) {
        final double minX = this.viewBox.getMin().getX() * SCALE_FACTOR;
        final double minY = this.viewBox.getMin().getY() * SCALE_FACTOR;
        final double width = (this.viewBox.getMax().getX() - this.viewBox.getMin().getX()) * SCALE_FACTOR;
        final double height = (this.viewBox.getMax().getY() - this.viewBox.getMin().getY()) * SCALE_FACTOR;
        final String elem = this.svgGenerator.getSVGElement(null, true, new ViewBox(minX, minY, width, height), null, MeetOrSlice.MEET);

        try {
            SVGUtils.writeToSVG(new File(filename), elem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

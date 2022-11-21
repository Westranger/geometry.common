package de.westranger.geometry.common.complex;

import de.westranger.geometry.common.simple.Point2D;

public interface Polygon {
    Polygon intersection(final Polygon poly);

    Polygon union(final Polygon poly);

    /**
     * @see <a href"https://www.tandfonline.com/doi/full/10.1080/10095020.2012.747643">An algorithm for generating geometric buffers for vector feature layers</a>
     */
    Polygon buffer(final double thickness);

    Polygon rotate(final Point2D rotationPoint, final double angle);

    boolean isWithin(final Point2D point);

    // TODO eigene funktion schreiben, die entlang der outline geht und schaut, obt es intersections mit einem anderen segment und liegt dieses segment innerhalb oder ausserhalb des polygons
    double areaSigned();
}

package de.westranger.geometry.common.operation.result;

import de.westranger.geometry.common.simple.Point2D;

import java.util.List;
import java.util.Optional;

public final class GeometryResult<T> extends PointResult {

    private final Optional<T> geometry;

    public GeometryResult(final List<Point2D> points, final T geometry) {
        super(points);
        if (geometry == null) {
            this.geometry = Optional.empty();
        } else {
            this.geometry = Optional.of(geometry);
        }
    }

    public GeometryResult(final List<Point2D> points) {
        this(points, null);
    }

    public GeometryResult(final T geometry) {
        this(null, geometry);
    }

    public GeometryResult() {
        this(null, null);
    }

    public Optional<T> getGeometry() {
        return this.geometry;
    }
}

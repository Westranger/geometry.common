package de.westranger.geometry.common.operation.result;

import de.westranger.geometry.common.simple.Point2D;

import java.util.List;
import java.util.Optional;

public class PointResult {
    private final Optional<List<Point2D>> points;

    public PointResult() {
        this.points = Optional.empty();
    }

    public PointResult(final List<Point2D> points) {
        if (points != null && !points.isEmpty()) {
            this.points = Optional.of(points);
        } else {
            this.points = Optional.empty();
        }
    }

    public Optional<List<Point2D>> getPoints() {
        return this.points;
    }

}

package de.westranger.geometry.common.simple;

import de.westranger.geometry.common.math.Vector2D;

public class ArcCircle extends Circle {
    public ArcCircle(Vector2D center, Vector2D arcStart, Vector2D arcEnd, double arcStartAngle, double arcLength, double radius) {
        super(center, arcStart, arcEnd, arcStartAngle, arcLength, radius);
    }
}

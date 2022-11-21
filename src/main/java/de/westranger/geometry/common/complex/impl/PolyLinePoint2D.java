package de.westranger.geometry.common.complex.impl;

import de.westranger.geometry.common.Side;
import de.westranger.geometry.common.complex.PolyLine;
import de.westranger.geometry.common.simple.Point2D;

import java.util.LinkedList;

public class PolyLinePoint2D extends LinkedList<Point2D> implements PolyLine {
    @Override
    public PolyLine computeBuffer(Side side, double width) {
        return null;
    }
}

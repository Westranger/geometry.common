package de.westranger.geometry.common.complex;

import de.westranger.geometry.common.Side;

public interface PolyLine {
    // TODO für 'segmente' und 'Segment + arcs' implementieren
    // TODO an den Enpunkten sollte einen Linie orthogonal verlaufen und das letzte segment so zu begrenezen
    // TODO wenn bei dem fall mit segments + arcs die width größer ist als der Radius eines der beinhalteten arcs, sprich der buffer durch den mittelpunkt des kreise vergößert wird, muss die width auf Radius begrenzt sein
    PolyLine computeBuffer(final Side side, double width);
}

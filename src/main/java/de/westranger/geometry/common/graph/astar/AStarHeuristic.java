package de.westranger.geometry.common.graph.astar;

public interface AStarHeuristic<T> {
    double distance(final T a, final T b);
}

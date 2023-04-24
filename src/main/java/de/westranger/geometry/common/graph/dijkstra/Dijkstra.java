package de.westranger.geometry.common.graph.dijkstra;

import de.westranger.geometry.common.graph.Graph;

public class Dijkstra<T extends Comparable<T>, G extends Comparable<G>> {

    protected final Graph<T, G> graph;

    public Dijkstra(final Graph<T, G> graph) {
        this.graph = graph;
    }

}

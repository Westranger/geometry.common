package de.westranger.geometry.common.optimization.stochastic;

import de.westranger.geometry.common.optimization.Solution;

public interface NeighbourSelector {
    Solution computeNeighbour(final Solution intermediateSolution, final int minTemp, final int amxTemp);
}

package kiwiland.application.service.conditions;

import kiwiland.application.datastructures.Path;

public class WeightPathFilter<V> implements BasePathFilter<V> {

    private final int maxWeight;

    public WeightPathFilter(final int maxWeight) {
	super();
	this.maxWeight = maxWeight;
    }

    @Override
    public boolean passFilter(final Path<V> path) {
	return path.getPathTotalWeight() < maxWeight;
    }

}

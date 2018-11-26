package kiwiland.application.service.conditions;

import kiwiland.application.datastructures.Path;

public class MaxHopsPathFilter<V> implements BasePathFilter<V> {

    private final int maxHops;

    public MaxHopsPathFilter(final int maxHops) {
	super();
	this.maxHops = maxHops;
    }

    @Override
    public boolean passFilter(final Path<V> path) {
	return path.getNumberOfHops() <= maxHops;
    }

}

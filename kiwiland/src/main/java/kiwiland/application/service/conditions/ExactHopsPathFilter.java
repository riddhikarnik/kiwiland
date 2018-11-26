package kiwiland.application.service.conditions;

import kiwiland.application.datastructures.Path;

public class ExactHopsPathFilter<V> implements BasePathFilter<V> {

    private final int hopsNumber;

    public ExactHopsPathFilter(final int hopsNumber) {
	super();
	this.hopsNumber = hopsNumber;
    }

    @Override
    public boolean passFilter(final Path<V> path) {
	return path.getNumberOfHops() == hopsNumber;
    }

}

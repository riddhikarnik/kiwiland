package kiwiland.application.service.conditions;

import kiwiland.application.datastructures.Path;

public class RepeatedEdgePathFilter<V> implements BasePathFilter<V> {

    @Override
    public boolean passFilter(final Path<V> path) {
	return !path.hasRepeatedEdges();
    }

}

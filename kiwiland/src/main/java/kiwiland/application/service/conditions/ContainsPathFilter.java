package kiwiland.application.service.conditions;

import kiwiland.application.datastructures.Path;

public class ContainsPathFilter<V> implements BasePathFilter<V> {
    private final Path<V> objectivePath;

    public ContainsPathFilter(final Path<V> objectivePath) {
	this.objectivePath = objectivePath;
    }

    @Override
    public boolean passFilter(final Path<V> path) {
	return objectivePath.startsWith(path);
    }

}

package kiwiland.application.service.conditions;

import kiwiland.application.datastructures.Path;

public interface BasePathFilter<V> {

    boolean passFilter(final Path<V> path);

}

package kiwiland.application.datastructures;

import java.util.List;
import java.util.Set;

import kiwiland.application.service.conditions.BasePathFilter;

public interface Graph<V> {

    boolean addEdge(V from, V to, int weight);

    boolean addVertex(V vertex);

    Edge<V> getEdge(V from, V to);

    Set<V> getAllVertex();

    List<Path<V>> getAllPaths(V startingNode, V endingNode, BasePathFilter<V> filter);

}

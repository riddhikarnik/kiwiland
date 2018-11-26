package kiwiland.application.datastructures;

import java.util.List;

public interface Path<V> extends Comparable<Path<V>> {

    void addEdge(Edge<V> edge);

    int getPathTotalWeight();

    int getNumberOfHops();

    V getLastNode();

    void removeLastEdge();

    List<Edge<V>> getEdgeList();

    boolean hasRepeatedEdges();

    boolean startsWith(Path<V> otherPath);

}
package kiwiland.application.datastructures;

public interface Edge<V> {

    V getStartingVertex();

    V getEndingVertex();

    int getWeight();
}

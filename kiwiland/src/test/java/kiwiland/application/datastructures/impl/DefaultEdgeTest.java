package kiwiland.application.datastructures.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import kiwiland.application.datastructures.Edge;

public class DefaultEdgeTest {
    private final Edge<String> edge = DefaultEdge.getWeightedEdge("A", "B", 15);

    @Test
    public void equalShouldNotConsiderWeight() {
	final Edge<String> edgeWithDifferentWeight = DefaultEdge.getWeightedEdge("A", "B", 5);
	assertTrue(edgeWithDifferentWeight.equals(edge));

	final Edge<String> differentEdge = DefaultEdge.getWeightedEdge("B", "C", 5);
	assertFalse(differentEdge.equals(edge));
    }

}

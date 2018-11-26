package kiwiland.application.filter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import kiwiland.application.datastructures.Path;
import kiwiland.application.datastructures.impl.DefaultEdge;
import kiwiland.application.datastructures.impl.GraphPath;
import kiwiland.application.service.conditions.BasePathFilter;
import kiwiland.application.service.conditions.MaxHopsPathFilter;

public class MaxHopsPathFilterTest {
    private final BasePathFilter<String> filter = new MaxHopsPathFilter<String>(3);

    @Test
    public void shouldPassWhenPathIsEmpty() {
	final Path<String> path = GraphPath.emptyPath();
	assertTrue(filter.passFilter(path));
    }

    @Test
    public void testPassFilter() {
	final Path<String> targetPath = GraphPath.emptyPath();
	targetPath.addEdge(DefaultEdge.getWeightedEdge("A", "B", 5));
	// The filter looks for a max of 3 hops so the previous path should pass
	assertTrue(filter.passFilter(targetPath));

	// It should also pass when the number of hops in the path is equal to
	// the max set in the filter
	targetPath.addEdge(DefaultEdge.getWeightedEdge("B", "C", 15));
	targetPath.addEdge(DefaultEdge.getWeightedEdge("C", "D", 25));
	assertTrue(filter.passFilter(targetPath));

	targetPath.addEdge(DefaultEdge.getWeightedEdge("D", "E", 5));
	// The path has 4 hops now so the filter should fail
	assertFalse(filter.passFilter(targetPath));

    }

}

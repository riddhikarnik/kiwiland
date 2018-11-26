package kiwiland.application.filter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import kiwiland.application.datastructures.Path;
import kiwiland.application.datastructures.impl.DefaultEdge;
import kiwiland.application.datastructures.impl.GraphPath;
import kiwiland.application.service.conditions.BasePathFilter;
import kiwiland.application.service.conditions.ExactHopsPathFilter;

public class ExactHopsPathFilterTest {
    private final BasePathFilter<String> filter = new ExactHopsPathFilter<String>(3);

    @Test
    public void testPassFilter() {
	final Path<String> targetPath = GraphPath.emptyPath();
	targetPath.addEdge(DefaultEdge.getWeightedEdge("A", "B", 5));
	targetPath.addEdge(DefaultEdge.getWeightedEdge("B", "C", 15));
	targetPath.addEdge(DefaultEdge.getWeightedEdge("C", "D", 25));
	assertTrue(filter.passFilter(targetPath));

	targetPath.removeLastEdge();
	assertFalse(filter.passFilter(targetPath));

    }

}

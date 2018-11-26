package kiwiland.application.filter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import kiwiland.application.datastructures.Path;
import kiwiland.application.datastructures.impl.DefaultEdge;
import kiwiland.application.datastructures.impl.GraphPath;
import kiwiland.application.service.conditions.BasePathFilter;
import kiwiland.application.service.conditions.ContainsPathFilter;

public class ContainsPathFilterTest {
    private BasePathFilter<String> filter;
    private Path<String> targetPath;

    @Before
    public void setUp() throws Exception {
	targetPath = GraphPath.emptyPath();
	targetPath.addEdge(DefaultEdge.getWeightedEdge("A", "B", 5));
	targetPath.addEdge(DefaultEdge.getWeightedEdge("B", "C", 15));
	targetPath.addEdge(DefaultEdge.getWeightedEdge("C", "D", 25));

	filter = new ContainsPathFilter<String>(targetPath);

    }

    @Test
    public void testPassFilter() {
	final Path<String> partialPath = GraphPath.emptyPath();
	partialPath.addEdge(DefaultEdge.getWeightedEdge("A", "B", 5));
	assertTrue(filter.passFilter(partialPath));

	partialPath.addEdge(DefaultEdge.getWeightedEdge("B", "D", 5));
	assertFalse(filter.passFilter(partialPath));

	assertTrue(filter.passFilter(GraphPath.copyPath(targetPath)));
    }

}

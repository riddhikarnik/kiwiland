package kiwiland.application.filter;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import kiwiland.application.datastructures.Path;
import kiwiland.application.datastructures.impl.DefaultEdge;
import kiwiland.application.datastructures.impl.GraphPath;
import kiwiland.application.service.conditions.BasePathFilter;
import kiwiland.application.service.conditions.WeightPathFilter;

public class WeightPathFilterTest {
    private final BasePathFilter<String> filter = new WeightPathFilter<String>(15);

    @Test
    public void testPassFilter() {
        final Path<String> targetPath = GraphPath.emptyPath();
        targetPath.addEdge(DefaultEdge.getWeightedEdge("A", "B", 5));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("B", "C", 2));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("C", "D", 3));

        // The weight of the path is 10 and the max weight on the filter is 15
        // So the filter should pass
        assertThat(filter.passFilter(targetPath)).isTrue();

        // When the weight of the path is equal or greater to the filter, it
        // should fail
        targetPath.addEdge(DefaultEdge.getWeightedEdge("D", "A", 5));
        assertThat(filter.passFilter(targetPath)).isFalse();

        targetPath.addEdge(DefaultEdge.getWeightedEdge("A", "E", 5));
        assertThat(filter.passFilter(targetPath)).isFalse();
    }

}

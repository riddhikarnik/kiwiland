package kiwiland.application.filter;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import kiwiland.application.datastructures.Path;
import kiwiland.application.datastructures.impl.DefaultEdge;
import kiwiland.application.datastructures.impl.GraphPath;
import kiwiland.application.service.conditions.BasePathFilter;
import kiwiland.application.service.conditions.RepeatedEdgePathFilter;

public class RepeatedEdgePathFilterTest {
    private final BasePathFilter<String> filter = new RepeatedEdgePathFilter<String>();

    @Test
    public void shouldPassWhenPathHasNoRepeatedEdges() {
        final Path<String> targetPath = GraphPath.emptyPath();
        targetPath.addEdge(DefaultEdge.getWeightedEdge("A", "B", 5));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("B", "C", 15));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("C", "D", 25));
        assertThat(filter.passFilter(targetPath)).isTrue();
    }

    @Test
    public void shouldFailWhenPathHasRepeatedEdges() {
        final Path<String> targetPath = GraphPath.emptyPath();
        targetPath.addEdge(DefaultEdge.getWeightedEdge("A", "B", 5));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("B", "C", 15));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("C", "D", 25));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("D", "A", 15));
        targetPath.addEdge(DefaultEdge.getWeightedEdge("A", "B", 5));
        assertThat(filter.passFilter(targetPath)).isFalse();
    }

}

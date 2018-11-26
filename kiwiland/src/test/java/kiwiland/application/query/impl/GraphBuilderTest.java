package kiwiland.application.query.impl;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import kiwiland.application.datastructures.Graph;
import kiwiland.application.query.Query;
import kiwiland.application.service.RailRoadService;

@RunWith(MockitoJUnitRunner.class)
public class GraphBuilderTest {
    @Mock
    private Graph<String> graph;
    @Mock
    private RailRoadService railRoadService;

    @Test
    public void shouldInvokeCommuterGraph() {
	final Query command = new GraphCommand("Graph: AB3, BC15");
	when(railRoadService.getAllRoutes()).thenReturn(graph);

	command.runQuery(railRoadService);

	verify(railRoadService, atLeastOnce()).getAllRoutes();

	verify(graph, times(1)).addVertex("A");
	// It tries to add B twice because it appears twice on the input line
	// The second invocation has no effect on the graph
	verify(graph, times(2)).addVertex("B");
	verify(graph, times(1)).addVertex("C");

	verify(graph).addEdge("A", "B", 3);
	verify(graph).addEdge("B", "C", 15);
    }

}

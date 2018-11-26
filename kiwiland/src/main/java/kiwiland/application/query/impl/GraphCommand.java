package kiwiland.application.query.impl;

import kiwiland.application.datastructures.Graph;
import kiwiland.application.query.base.BaseQuery;
import kiwiland.application.service.RailRoadService;

public class GraphCommand extends BaseQuery {

    public GraphCommand(final String commandLine) {
	super(commandLine);
    }

    @Override
    public void runQuery(final RailRoadService railRoadService) {
	final String nodesLine = getCommandLine().substring(7);
	final String[] nodes = nodesLine.split(",");
	for (final String vertexEdgePair : nodes) {
	    addNodes(railRoadService.getAllRoutes(), vertexEdgePair);
	}

    }

    private void addNodes(final Graph<String> graph, final String vertexEdgePair) {
	final String trimmedPair = vertexEdgePair.trim();
	final String from = String.valueOf(trimmedPair.charAt(0));
	final String to = String.valueOf(trimmedPair.charAt(1));
	final int weight = Integer.valueOf(trimmedPair.substring(2));
	graph.addVertex(from);
	graph.addVertex(to);
	graph.addEdge(from, to, weight);
    }

}

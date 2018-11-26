package kiwiland.application.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kiwiland.application.datastructures.Graph;
import kiwiland.application.datastructures.Path;
import kiwiland.application.datastructures.impl.DefaultEdge;
import kiwiland.application.datastructures.impl.GraphPath;
import kiwiland.application.service.RailRoadService;
import kiwiland.application.service.conditions.BasePathFilter;
import kiwiland.application.service.conditions.ContainsPathFilter;
import kiwiland.application.service.conditions.ExactHopsPathFilter;
import kiwiland.application.service.conditions.MaxHopsPathFilter;
import kiwiland.application.service.conditions.RepeatedEdgePathFilter;
import kiwiland.application.service.conditions.WeightPathFilter;

public final class RailRoadServiceImpl implements RailRoadService {

    private final Graph<String> routes;

    public RailRoadServiceImpl(final Graph<String> routes) {
	super();
	this.routes = routes;
    }

    @Override
    public int numberOfPathsWithExactStops(final String startingCity, final String destinationCity, final int stops) {
	final List<Path<String>> paths = routes.getAllPaths(startingCity, destinationCity,
		new MaxHopsPathFilter<String>(stops));
	final List<Path<String>> exactPaths = new ArrayList<Path<String>>();
	final BasePathFilter<String> exactFilter = new ExactHopsPathFilter<String>(stops);
	for (final Path<String> each : paths) {
	    if (exactFilter.passFilter(each)) {
		exactPaths.add(each);
	    }
	}
	return exactPaths.size();
    }

    @Override
    public int numberOfPathsWithMaxStops(final String startingCity, final String destinationCity, final int stops) {
	return routes.getAllPaths(startingCity, destinationCity, new MaxHopsPathFilter<String>(stops)).size();
    }

    @Override
    public int numberOfPathsWithMaxWeight(final String startingCity, final String destinationCity, final int weight) {
	return routes.getAllPaths(startingCity, destinationCity, new WeightPathFilter<String>(weight)).size();
    }

    @Override
    public int getShortestDistance(final String startingCity, final String destinationCity) {
	final List<Path<String>> allPaths = routes.getAllPaths(startingCity, destinationCity,
		new RepeatedEdgePathFilter<String>());
	return Collections.min(allPaths).getPathTotalWeight();
    }

    @Override
    public int getDistance(final String startingCity, final String destinationCity,
	    final List<String> intermediateNodes) {
	final Path<String> objectivePath = createPath(startingCity, destinationCity, intermediateNodes);
	final List<Path<String>> allPaths = routes.getAllPaths(startingCity, destinationCity,
		new ContainsPathFilter<String>(objectivePath));

	return allPaths.get(0).getPathTotalWeight();
    }

    private Path<String> createPath(final String from, final String to, final List<String> intermediateNodes) {
	final Path<String> resultingPath = GraphPath.emptyPath();
	String currentNode = from;
	if (intermediateNodes != null) {
	    for (final String eachIntermediate : intermediateNodes) {
		resultingPath.addEdge(DefaultEdge.getWeightedEdge(currentNode, eachIntermediate, 0));
		currentNode = eachIntermediate;
	    }
	}
	resultingPath.addEdge(DefaultEdge.getWeightedEdge(currentNode, to, 0));
	return resultingPath;
    }

    @Override
    public Graph<String> getAllRoutes() {
	return routes;
    }

}

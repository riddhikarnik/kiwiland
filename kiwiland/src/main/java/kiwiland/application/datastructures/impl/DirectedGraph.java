package kiwiland.application.datastructures.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kiwiland.application.datastructures.Edge;
import kiwiland.application.datastructures.Graph;
import kiwiland.application.datastructures.Path;
import kiwiland.application.exception.NoSuchRouteException;
import kiwiland.application.service.conditions.BasePathFilter;

public class DirectedGraph<V> implements Graph<V> {

    private final Map<V, Set<Edge<V>>> edges = new HashMap<V, Set<Edge<V>>>();

    @Override
    public boolean addVertex(final V vertex) {
	assertVertexNotNull(vertex);
	if (!edges.containsKey(vertex)) {
	    edges.put(vertex, new LinkedHashSet<Edge<V>>());
	    return true;
	}
	return false;
    }

    private void assertVertexNotNull(final V vertex) {
	if (vertex == null) {
	    throw new IllegalArgumentException("Vertex can not be null");
	}
    }

    @Override
    public boolean addEdge(final V from, final V to, final int weight) {
	assertVertexExists(from);
	assertVertexExists(to);

	final Edge<V> newEdge = DefaultEdge.getWeightedEdge(from, to, weight);

	final Set<Edge<V>> sourceEdges = edges.get(from);
	if (sourceEdges.contains(newEdge)) {
	    sourceEdges.remove(newEdge);
	}
	return edges.get(from).add(newEdge);
    }

    private void assertVertexExists(final V vertex) {
	if (!edges.containsKey(vertex)) {
	    throw new IllegalArgumentException("Vertex " + vertex.toString() + " does not exist");
	}
    }

    @Override
    public Edge<V> getEdge(final V from, final V to) {
	assertVertexExists(from);
	assertVertexExists(to);

	final Set<Edge<V>> startingVertexEdges = edges.get(from);
	for (final Edge<V> eachEdge : startingVertexEdges) {
	    if (eachEdge.getEndingVertex().equals(to)) {
		return eachEdge;
	    }
	}
	return null;
    }

    @Override
    public List<Path<V>> getAllPaths(final V startingNode, final V endingNode, final BasePathFilter<V> filter) {
	assertVertexExists(startingNode);
	assertVertexExists(endingNode);

	final List<Path<V>> paths = new ArrayList<Path<V>>();
	for (final Edge<V> each : edges.get(startingNode)) {
	    final Path<V> path = GraphPath.emptyPath();
	    path.addEdge(each);
	    paths.addAll(search(path, filter, endingNode));
	}

	if (paths.isEmpty()) {
	    throw new NoSuchRouteException(startingNode.toString(), endingNode.toString());
	}
	return paths;
    }

    private List<Path<V>> search(final Path<V> path, final BasePathFilter<V> filter, final V end) {
	final List<Path<V>> paths = new ArrayList<Path<V>>();
	if (filter.passFilter(path)) {
	    if (hasReachedEnd(path, end)) {
		paths.add(GraphPath.copyPath(path));
	    }
	    for (final Edge<V> each : edges.get(path.getLastNode())) {
		path.addEdge(each);
		paths.addAll(search(path, filter, end));
	    }

	}
	path.removeLastEdge();
	return paths;
    }

    private boolean hasReachedEnd(final Path<V> path, final V end) {
	return path.getLastNode().equals(end);
    }

    @Override
    public Set<V> getAllVertex() {
	return Collections.unmodifiableSet(edges.keySet());
    }

}

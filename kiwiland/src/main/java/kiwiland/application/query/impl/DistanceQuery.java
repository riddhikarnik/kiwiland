package kiwiland.application.query.impl;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import kiwiland.application.exception.NoSuchRouteException;
import kiwiland.application.query.base.BaseQuery;
import kiwiland.application.service.RailRoadService;

public class DistanceQuery extends BaseQuery {

    private final PrintStream outputStream;

    public DistanceQuery(final String queryName, final PrintStream stream) {
	super(queryName);
	this.outputStream = stream;
    }

    @Override
    public void runQuery(final RailRoadService commuter) {
	final String routeLine = getCommandLine().substring(10);
	final String[] nodes = routeLine.split("-");
	try {
	    outputStream.println(commuter.getDistance(nodes[0], nodes[nodes.length - 1], getIntermediateList(nodes)));
	} catch (final NoSuchRouteException e) {
	    outputStream.println(NO_ROUTE_MSG);
	}
    }

    private List<String> getIntermediateList(final String[] nodes) {
	final List<String> intermediateList = new ArrayList<String>();
	for (int i = 1; i < nodes.length - 1; i++) {
	    intermediateList.add(nodes[i]);
	}
	return intermediateList;
    }
}

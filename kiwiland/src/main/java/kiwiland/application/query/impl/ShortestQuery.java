package kiwiland.application.query.impl;

import java.io.PrintStream;

import kiwiland.application.exception.NoSuchRouteException;
import kiwiland.application.query.base.BaseQuery;
import kiwiland.application.service.RailRoadService;

public class ShortestQuery extends BaseQuery {

    private final PrintStream outputStream;

    public ShortestQuery(final String commandLine, final PrintStream stream) {
	super(commandLine);
	this.outputStream = stream;
    }

    @Override
    public void runQuery(final RailRoadService commuter) {
	final String routeLine = getCommandLine().substring(10);
	final String from = String.valueOf(routeLine.charAt(0));
	final String to = String.valueOf(routeLine.charAt(2));
	try {
	    outputStream.println(commuter.getShortestDistance(from, to));
	} catch (final NoSuchRouteException e) {
	    outputStream.println(NO_ROUTE_MSG);
	}
    }
}

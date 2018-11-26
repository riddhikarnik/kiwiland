package kiwiland.application.query.impl;

import java.io.PrintStream;

import kiwiland.application.query.base.BaseQuery;
import kiwiland.application.service.RailRoadService;

public class TripsQuery extends BaseQuery {
    private final PrintStream outputStream;

    public TripsQuery(final String commandLine, final PrintStream stream) {
	super(commandLine);
	this.outputStream = stream;
    }

    @Override
    public void runQuery(final RailRoadService railRoadService) {
	final String routeLine = getCommandLine().substring(7);
	final String[] queryParts = routeLine.split(",");

	final String filterCriteria = queryParts[0];
	final int filterValue = Integer.valueOf(queryParts[1]);
	final String startNode = String.valueOf(queryParts[2].charAt(0));
	final String endNode = String.valueOf(queryParts[2].charAt(2));

	int numberOfTrips = 0;

	try {
	    if (filterCriteria.equalsIgnoreCase("MAX_STOPS")) {
		numberOfTrips = railRoadService.numberOfPathsWithMaxStops(startNode, endNode, filterValue);
	    } else if (filterCriteria.equalsIgnoreCase("EXACT_STOPS")) {
		numberOfTrips = railRoadService.numberOfPathsWithExactStops(startNode, endNode, filterValue);
	    } else if (filterCriteria.equalsIgnoreCase("MAX_DISTANCE")) {
		numberOfTrips = railRoadService.numberOfPathsWithMaxWeight(startNode, endNode, filterValue);
	    }
	    outputStream.println(numberOfTrips);
	} catch (final Exception e) {
	    outputStream.println(NO_ROUTE_MSG);
	}

    }

}

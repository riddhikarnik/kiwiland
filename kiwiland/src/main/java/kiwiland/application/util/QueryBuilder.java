package kiwiland.application.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import kiwiland.application.query.Query;
import kiwiland.application.query.impl.DistanceQuery;
import kiwiland.application.query.impl.GraphCommand;
import kiwiland.application.query.impl.ShortestQuery;
import kiwiland.application.query.impl.TripsQuery;

public class QueryBuilder {
    private static final String GRAPH_REGEX = "GRAPH:\\s(\\D\\D\\d+)(, \\D\\D\\d+)*";
    private static final String DISTANCE_REGEX = "DISTANCE:\\s\\D-\\D(-\\D)*";
    private static final String TRIPS_REGEX = "TRIPS:\\s(MAX_STOPS|EXACT_STOPS|MAX_DISTANCE),(\\d)+,\\D-\\D";
    private static final String SHORTEST_REGEX = "SHORTEST:\\s\\D-\\D";

    private final PrintStream outputStream;

    public QueryBuilder(final PrintStream outputStream) {
	this.outputStream = outputStream;
    }

    public List<Query> parseInput(final File inputFile) throws IOException {
	final List<Query> queryList = new ArrayList<Query>();
	final BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	while (reader.ready()) {
	    final Query query = parse(reader);
	    if (query != null) {
		queryList.add(query);
	    }
	}
	reader.close();
	return queryList;
    }

    private Query parse(final BufferedReader reader) throws IOException {
	Query inputQuery = null;
	final String lineOfText = reader.readLine().toUpperCase();
	if (lineOfText.matches(GRAPH_REGEX)) {
	    inputQuery = new GraphCommand(lineOfText);
	} else if (lineOfText.matches(SHORTEST_REGEX)) {
	    inputQuery = new ShortestQuery(lineOfText, outputStream);
	} else if (lineOfText.matches(TRIPS_REGEX)) {
	    inputQuery = new TripsQuery(lineOfText, outputStream);
	} else if (lineOfText.matches(DISTANCE_REGEX)) {
	    inputQuery = new DistanceQuery(lineOfText, outputStream);
	} else {
	    outputStream.println("Invalid query : " + lineOfText);
	}
	return inputQuery;
    }

}

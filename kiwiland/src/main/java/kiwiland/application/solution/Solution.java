package kiwiland.application.solution;

import java.io.File;
import java.util.List;

import kiwiland.application.query.Query;
import kiwiland.application.service.RailRoadService;
import kiwiland.application.service.impl.RailRoadServiceImpl;
import kiwiland.application.util.GraphBuilder;
import kiwiland.application.util.QueryBuilder;

public class Solution {

    public static void main(final String[] args) throws Exception {
	final RailRoadService railRoadService = new RailRoadServiceImpl(GraphBuilder.getEmptyGraph());
	File inputFile = null;
	if (args.length == 1) {
	    inputFile = new File(args[0]);
	} else {
	    inputFile = new File(Solution.class.getResource("/trains.txt").toURI());
	}

	final List<Query> inputCommands = new QueryBuilder(System.out).parseInput(inputFile);
	for (final Query eachCommand : inputCommands) {
	    eachCommand.runQuery(railRoadService);
	}
    }

}

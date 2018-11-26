package kiwiland.application.query.impl;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import kiwiland.application.query.Query;
import kiwiland.application.util.QueryBuilder;

@RunWith(MockitoJUnitRunner.class)
public class QueryBuilderTest {
    @Mock
    private PrintStream outputStream;
    private QueryBuilder builder;

    @Before
    public void initCommandBuilder() {
	builder = new QueryBuilder(outputStream);
    }

    @Test
    public void shouldReadEveryCommandFromFile() throws URISyntaxException, IOException {
	final File inputFile = new File(getClass().getResource("/graphInputTest01.txt").toURI());

	assertThat(builder.parseInput(inputFile)).isEqualTo(getExpectedCommands());
    }

    private List<Query> getExpectedCommands() {
	final List<Query> expectedCommands = new ArrayList<Query>();
	expectedCommands.add(new GraphCommand("GRAPH: AB15, BC4, CD8, DC8, DE6, AD5, CE12, EB3, AE7"));
	expectedCommands.add(new DistanceQuery("DISTANCE: A-B-C", outputStream));
	expectedCommands.add(new TripsQuery("TRIPS: MAX_STOPS,3,C-C", outputStream));
	expectedCommands.add(new TripsQuery("TRIPS: EXACT_STOPS,4,A-C", outputStream));
	expectedCommands.add(new ShortestQuery("SHORTEST: B-B", outputStream));
	expectedCommands.add(new TripsQuery("TRIPS: MAX_DISTANCE,30,C-C", outputStream));
	return expectedCommands;
    }

}

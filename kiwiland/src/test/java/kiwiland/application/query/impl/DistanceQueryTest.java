package kiwiland.application.query.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import kiwiland.application.exception.NoSuchRouteException;
import kiwiland.application.query.Query;
import kiwiland.application.query.base.BaseQuery;
import kiwiland.application.service.RailRoadService;

@RunWith(MockitoJUnitRunner.class)
public class DistanceQueryTest {
    // Mock the commuter to later verify that it was called
    // with the appropriate parameters
    @Mock
    private RailRoadService commuter;
    // Same with the PrintStream
    @Mock
    private PrintStream outputStream;
    private Query distanceCommand;

    @Before
    public void intCommand() {
	distanceCommand = new DistanceQuery("DISTANCE: A-B-C", outputStream);
    }

    @Test
    public void shouldInvokeRouteDistance() {
	final List<String> intermediateNodes = new ArrayList<String>();
	intermediateNodes.add("B");
	// Set the commuter to return a given value when called with the
	// appropriate parameters to then verify that the same value was
	// printed to the stream
	final int routeDistance = 5;
	when(commuter.getDistance("A", "C", intermediateNodes)).thenReturn(routeDistance);

	distanceCommand.runQuery(commuter);

	verify(outputStream).println(routeDistance);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldPrintNoRouteWhenExceptionIsThrown() {
	final List<String> intermediateNodes = new ArrayList<String>();
	intermediateNodes.add("B");
	when(commuter.getDistance("A", "C", intermediateNodes)).thenThrow(NoSuchRouteException.class);

	distanceCommand.runQuery(commuter);

	verify(outputStream).println(BaseQuery.NO_ROUTE_MSG);
    }

}

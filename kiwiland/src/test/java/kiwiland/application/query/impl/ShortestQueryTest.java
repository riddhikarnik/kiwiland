package kiwiland.application.query.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;

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
public class ShortestQueryTest {
    @Mock
    private RailRoadService railRoadService;
    @Mock
    private PrintStream stream;
    private Query query;

    @Before
    public void initCommand() {
	query = new ShortestQuery("SHORTEST: A-E", stream);
    }

    @Test
    public void shouldInvokeShortestDistance() {
	final int distance = 5;
	when(railRoadService.getShortestDistance("A", "E")).thenReturn(distance);

	query.runQuery(railRoadService);

	verify(stream).println(distance);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldPrintNoRouteWhenExceptionIsThrown() {
	when(railRoadService.getShortestDistance("A", "E")).thenThrow(NoSuchRouteException.class);

	query.runQuery(railRoadService);

	verify(stream).println(BaseQuery.NO_ROUTE_MSG);
    }

}

package kiwiland.application.query.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import kiwiland.application.query.Query;
import kiwiland.application.service.RailRoadService;

@RunWith(MockitoJUnitRunner.class)
public class TripsCommandTest {
    private Query query;
    @Mock
    private RailRoadService railRoadService;
    @Mock
    private PrintStream stream;

    @Test
    public void shouldInvokeMaxStops() {
	query = new TripsQuery("TRIPS: MAX_STOPS,3,C-C", stream);
	final int stops = 5;
	when(railRoadService.numberOfPathsWithMaxStops("C", "C", 3)).thenReturn(stops);

	query.runQuery(railRoadService);

	verify(stream).println(stops);
    }

    @Test
    public void shouldInvokeMaxWeight() {
	query = new TripsQuery("TRIPS: MAX_DISTANCE,30,B-C", stream);
	final int weight = 5;
	when(railRoadService.numberOfPathsWithMaxWeight("B", "C", 30)).thenReturn(weight);

	query.runQuery(railRoadService);

	verify(stream).println(weight);
    }

    @Test
    public void shouldInvokeExactStops() {
	query = new TripsQuery("TRIPS: EXACT_STOPS,4,A-C", stream);
	final int stops = 5;
	when(railRoadService.numberOfPathsWithExactStops("A", "C", 4)).thenReturn(stops);

	query.runQuery(railRoadService);

	verify(stream).println(stops);
    }

}

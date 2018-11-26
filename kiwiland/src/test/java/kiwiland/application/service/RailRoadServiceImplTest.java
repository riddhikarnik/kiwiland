package kiwiland.application.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import kiwiland.application.exception.NoSuchRouteException;
import kiwiland.application.service.impl.RailRoadServiceImpl;
import kiwiland.application.util.GraphBuilder;

public class RailRoadServiceImplTest {
    private final RailRoadService railRoadService = new RailRoadServiceImpl(GraphBuilder.getDefaultGraph());

    @Test
    public void distanceForABCShouldBe9() {
	assertEquals("The distance for the route A-B-C should be 9", 9,
		railRoadService.getDistance("A", "C", getIntermediateList("B")));
    }

    @Test
    public void distanceForADShouldBe5() {
	assertEquals("The distance for the route A-D should be 5", 5, railRoadService.getDistance("A", "D", null));
    }

    @Test
    public void distanceForADCShouldBe13() {
	assertEquals("The distance for the route A-D-C should be 13", 13,
		railRoadService.getDistance("A", "C", getIntermediateList("D")));
    }

    @Test
    public void distanceForAEBCDShouldBe22() {
	assertEquals("The distance for the route A-B-C should be 22", 22,
		railRoadService.getDistance("A", "D", getIntermediateList("E", "B", "C")));
    }

    @Test(expected = NoSuchRouteException.class)
    public void distanceForAEDShouldThrowNoSuchRouteException() {
	railRoadService.getDistance("A", "D", getIntermediateList("E"));
    }

    @Test
    public void numberOfTripsBetweenCandCWithMax3StopsShouldBe2() {
	assertEquals("There should be 2 trips between C and C with a maximum of 3 stops", 2,
		railRoadService.numberOfPathsWithMaxStops("C", "C", 3));
    }

    @Test
    public void numberOfTripsBetweenAandCWith4StopsShouldBe3() {
	assertEquals("There should be 3 trips between A and C with exactly 4 stops", 3,
		railRoadService.numberOfPathsWithExactStops("A", "C", 4));
    }

    @Test
    public void shortestDistanceFromAtoCShouldBe9() {
	assertEquals("The shortest distance from A to C should be 9", 9, railRoadService.getShortestDistance("A", "C"));
    }

    @Test
    public void shortestDistanceFromBtoBShouldBe9() {
	assertEquals("The shortest distance from B to B should be 9", 9, railRoadService.getShortestDistance("B", "B"));
    }

    @Test
    public void numberOfRoutesBetweenCandCshouldBe7() {
	assertEquals("There should be 7 routes between C and C with a distance less than 30", 7,
		railRoadService.numberOfPathsWithMaxWeight("C", "C", 30));
    }

    private List<String> getIntermediateList(final String... nodes) {
	final List<String> intermediateList = new ArrayList<String>();
	for (final String each : nodes) {
	    intermediateList.add(each);
	}
	return intermediateList;
    }
}

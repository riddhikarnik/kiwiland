package kiwiland.application.service;

import java.util.List;

import kiwiland.application.datastructures.Graph;

public interface RailRoadService {

    int getDistance(String startingCity, String destinationCity, List<String> intermediateCities);

    int getShortestDistance(String startingCity, String destinationCity);

    int numberOfPathsWithMaxStops(String startingCity, String destinationCity, int stops);

    int numberOfPathsWithMaxWeight(String startingCity, String destinationCity, int weight);

    int numberOfPathsWithExactStops(String startingCity, String destinationCity, int stops);

    Graph<String> getAllRoutes();

}

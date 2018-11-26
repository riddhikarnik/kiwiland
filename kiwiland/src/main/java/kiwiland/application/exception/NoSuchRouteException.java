package kiwiland.application.exception;

public class NoSuchRouteException extends RuntimeException {

    private static final long serialVersionUID = 6767092076531587534L;

    public NoSuchRouteException(final String startingVertex, final String endingVertex) {
	super(new StringBuffer("No such route between ").append(startingVertex).append(" and ").append(endingVertex)
		.toString());
    }
}

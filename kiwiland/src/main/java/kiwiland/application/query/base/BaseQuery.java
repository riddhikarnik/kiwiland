package kiwiland.application.query.base;

import kiwiland.application.query.Query;

public abstract class BaseQuery implements Query {

    public static final String NO_ROUTE_MSG = "NO SUCH ROUTE";
    private final String queryName;

    public BaseQuery(final String queryName) {
	this.queryName = queryName;
    }

    protected final String getCommandLine() {
	return queryName;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (queryName == null ? 0 : queryName.hashCode());
	return result;
    }

    @Override
    public boolean equals(final Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final BaseQuery other = (BaseQuery) obj;
	if (queryName == null) {
	    if (other.queryName != null) {
		return false;
	    }
	} else if (!queryName.equals(other.queryName)) {
	    return false;
	}
	return true;
    }

}

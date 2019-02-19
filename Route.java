/**
 * Class to instantiate Edges between Nodes (Routes between Cities)
 * Each Route has an Origin City and a Destination City as well as a travel time between the two
 * @author psing
 *
 */
public class Route {
	private City destination;
	private City origin;
	private int distance;
	
	/**
	 * Instantiates the Routes Class
	 * @param destination
	 * @param origin
	 * @param distance
	 */
	public Route (City destination, City origin, int distance) {
		this.destination = destination;
		this.origin = origin;
		this.distance = distance;
	}
	
	/**
	 * Gets the destination city in the route
	 * @return
	 */
	public City getDestination() {
		return destination;
	}
	
	/**
	 * Gets the origin city in the route
	 * @return
	 */
	public City getOrigin() {
		return origin;
	}
	
	/**
	 * Gets the distance between two Cities
	 * @return
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * Returns the goal or starting City depending on the input city and if they are connected
	 * @param given
	 * @return
	 */
	public City getConnected(City given) {
		if (given.getName().equals(destination.getName())) {
			return origin;
		} else if (given.getName().equals(origin.getName())){
			return destination;
		} else {
			return null; 
		}
	}
}

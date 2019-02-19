/**
 * Class to hold the required shipping paths as stated in the input
 * Each path will have an Origin City and a Destination City
 * @author psing
 *
 */
public class ShipRoute {
	private City origin;
	private City destination;
	
	/**
	 * Instantiates the ShipRoute Class
	 * @param origin
	 * @param destination
	 */
	public ShipRoute(City origin, City destination) {
		this.origin = origin;
		this.destination = destination;
	}
	
	/**
	 * Instantiates the ShipRoute Class with another ShipRoute (Duplicates ShipRoute s)
	 * @param s
	 */
	public ShipRoute(ShipRoute s) {
		this.origin = new City(s.getOrigin());
		this.destination = new City(s.getDestination());
	}
	
	/**
	 * Obtain the Origin City within a ShipRoute
	 * @return
	 */
	public City getOrigin() {
		return origin;
	}
	
	/**
	 * Obtain the Destination City within a ShipRoute
	 * @return
	 */
	public City getDestination() {
		return destination;
	}
	
	/**
	 * toString override method
	 */
	public String toString() {
		return origin.getName() + " " + destination.getName();
	}
}

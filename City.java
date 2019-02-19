import java.util.ArrayList;
/**
 * Class to instantiate Nodes (Cities)
 * Each City has a name, a refuelling time and hold an array of Routes to other Cities
 * @author psing
 *
 */
public class City {
	private String name;
	private int refuelTime;
	private ArrayList<Route> routes;
	
	/**
	 * Instantiates the City Class
	 * @param name
	 * @param refuelTime
	 */
	public City (String name, int refuelTime) {
		this.name = name;
		this.refuelTime = refuelTime;
		this.routes = new ArrayList<Route>();
	}
	
	/**\
	 * Instantiates the City Class (duplicate City c)
	 * @param c
	 */
	public City (City c) {
		this.name = c.name;
		this.refuelTime = c.refuelTime;
		this.routes = new ArrayList<Route>(c.routes);
	}
	
	/**
	 * Adds a route between two cities
	 * @param to
	 * @param distance
	 */
	public void addRoute(City to, int distance) {
		routes.add(new Route (to, this, distance));
	}
	
	/**
	 * Gets the name of a City
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the refuel time in a City
	 * @return
	 */
	public int getRefuelTime() {
		return refuelTime;
	}
	
	/**
	 * Gets the routes
	 * @return
	 */
	public ArrayList<Route> getRoutes() {
		return routes;
	}
	
	/**
	 * Override method toString
	 */
	public String toString() {
		return this.name;
	}
}

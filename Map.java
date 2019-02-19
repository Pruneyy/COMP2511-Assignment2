import java.util.ArrayList;

/**
 * Class to instantiate a Map for the layout of Cities (nodes) and Routes (edges)
 * @author psing
 *
 */
public class Map implements Graph<City>{
	private ArrayList<City> cities;
	
	/**
	 * Instantiates the Map Class
	 */
	public Map() {
		cities = new ArrayList<City>();
	}
	
	/**
	 * Add a City to the Map
	 * @param n
	 */
	@Override
	public void addNode(City n) {
		cities.add(n);
	}
	
	/**
	 * Add a Route to the Map
	 * @param origin
	 * @param destination
	 * @param distance
	 */
	@Override
	public void addEdge (City origin, City destination, int distance) {
		origin.addRoute(destination,  distance);
		destination.addRoute(origin,  distance);
	}
	
	/**
	 * Finds a City in the Map
	 * @param name
	 * @return
	 */
	public City getNode(String name) {
		for (City c:cities) {
			if (c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Prints the Map
	 */
	public void printMap() {
		for (City c:cities) {
			for (Route r:c.getRoutes()) {
				System.out.println(r.getOrigin().getName() + "(" + r.getOrigin().getRefuelTime() + ")" + " " + "-->" + " " + r.getDistance() + " " + "-->" + " " + r.getDestination().getName() + "(" + r.getDestination().getRefuelTime() + ")");
			}
		}
	}
	
	/**
	 * Gets a list of all the Cities in the Map
	 * @return
	 */
	public ArrayList<City> getCities() {
		return cities;
	}
	
	
}

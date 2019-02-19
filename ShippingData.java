import java.util.ArrayList;
/**
 * Create and Instantiate ShippingData Class which holds a lot of information for the AStarSearch
 * Contains the running cost of the current path, a list of Cities in the current path, a list of ShippingRoutes remaining 
 * and an integer count of the ShippingRoutes remaining
 * @author psing
 *
 */

public class ShippingData implements Comparable<ShippingData>{
	int cost;
	ArrayList<City> path;
	ArrayList<ShipRoute> shipmentsRemaining;
	int countShipmentsRemaining;
	
	/**
	 * Instantiates the ShippingData Class
	 * @param cost
	 * @param shipmentsRemaining
	 * @param countShipmentsRemaining
	 */
	public ShippingData(int cost, ArrayList<ShipRoute> shipmentsRemaining, int countShipmentsRemaining) {
		this.cost = cost;
		this.path = new ArrayList<City>();
		this.shipmentsRemaining = new ArrayList<ShipRoute>(shipmentsRemaining);
		this.countShipmentsRemaining = countShipmentsRemaining;
	}
	
	/**
	 * Instantiates the ShippingData Class by cloning a given ShippingData input
	 * @param sd
	 */
	public ShippingData(ShippingData sd) {
		this.cost = sd.cost;
		this.path = new ArrayList<City>(sd.path);
		this.shipmentsRemaining = new ArrayList<ShipRoute>(sd.shipmentsRemaining);
		this.countShipmentsRemaining = sd.countShipmentsRemaining;
	}
	
	/**
	 * Updates the path cost running total
	 * @param cost
	 */
	public void addCost(int cost) {
		this.cost = this.cost + cost;
	}
	
	/**
	 * Adds ShipmentsRemaining data to the Class
	 * @param shipmentsRemaining
	 */
	public void addShipmentsRemaining(ArrayList<ShipRoute> shipmentsRemaining) {
		this.shipmentsRemaining = shipmentsRemaining;
	}
	
	/**
	 * Adds a count of ShipmentsRemaining to the Class
	 * @param countShipmentsRemaining
	 */
	public void addCountShipmentsRemaining(int countShipmentsRemaining) {
		this.countShipmentsRemaining = countShipmentsRemaining;
	}
	
	/**
	 * Adds a path to the Class (update the ongoing paths)
	 * @param city
	 */
	public void addPath(City city) {
		path.add(city);
	}
	
	/**
	 * Obtains the current cost of the path taken
	 * @return
	 */
	public int getCost() {
		return cost;
	}
	
	/**
	 * Obtains a list of the remaining Shipments
	 * @return
	 */
	public ArrayList<ShipRoute> getShipmentsRemaining() {
		return shipmentsRemaining;
	}
	
	/**
	 * Obtains a count of the remaining Shipments
	 * @return
	 */
	public int getCountShipmentsRemaining() {
		return countShipmentsRemaining;
	}
	
	/**
	 * Obtains a list of the current Path taken
	 * @return
	 */
	public ArrayList<City> getPath() {
		return path;
	}
	
	/**
	 * Obtains the name of the City we are currently at
	 * @return
	 */
	public City getCurrentCity() {
		return path.get(path.size()-1);
	}
	
	/**
	 * A function to find and update the cost of the path
	 * @param start
	 * @param end
	 */
	public void calculateCost(City start, City end) {
		if (start.getRoutes().size() == 0) {
			System.out.println("Routes is empty");
		}
		if (start.getName().equals(end.getName())) {
			this.addCost(0);
			return;
		}
		for (Route r: start.getRoutes()) {
			if (r.getDestination().getName().equals(end.getName())) {
				this.addCost(start.getRefuelTime() + r.getDistance());
				return;
			}
		}
		System.out.println("Cost could not be calculated");
	}
	
	/**
	 * Removes a delivered ShipRoute once it's been completed and updates the ShipmentsRemaining counter
	 * @param delivered
	 */
	public void removeShipments(ShipRoute delivered) {
		shipmentsRemaining.remove(delivered);
		countShipmentsRemaining--;
	}
	
	/**
	 * Prints out the Shipping Route in the required format
	 * @param path
	 */
	public void finalPath(ArrayList<City> path) {
		for (int i = 0; i < path.size() - 1; i++) {
			System.out.println("Ship " + this.path.get(i).toString() + " to " + this.path.get(i + 1).toString());
		}
	}
	
	/**
	 * ToString override method
	 */
	public String toString() {
		return this.cost + this.path.toString();
	}
	
	/**
	 * CompareTo method for PriorityQueue logic
	 */
	public int compareTo(ShippingData sd) {
		if (cost < sd.getCost()) {
			return -1;
		}
		else {
			return 1;
		}
	}
}

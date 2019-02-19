import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
/**
 * Main function to calculate a shipping plan route with an AStarSearch
 * Reads in input data and adds it to the ShipmentPlanner Class which holds a map of the system
 * A* Search is run on this data to obtain the best shipping route to take
 * @author psing
 *
 */

public class ShipmentPlanner {
	Map m;
	static ArrayList<ShipRoute> shipments;
	
	/**
	 * Instantiates ShipmentPlanner Class
	 */
	public ShipmentPlanner() {
		this.m = new Map();
		shipments = new ArrayList<ShipRoute>();
	}
	
	/**
	 * Main function to read input file and call A* Search
	 * @param args
	 */
	public static void main(String[] args) {
		ShipmentPlanner sp = new ShipmentPlanner();
		Scanner sc = null;
		try {
			sc = new Scanner(new File(args[0]));
	        	while(sc.hasNextLine()) {
	        		String raw = sc.nextLine();
	        		String[] input = raw.split(" ");
	        		switch(input[0]) {
	        			//First Case: Refuelling --> Adds City Nodes to Map
		          		case "Refuelling":
		          			sp.addCity(input[1], input[2]);
		          			break;
		          		
		          		//Second Case: Time --> Adds Route Edges to Map
		          		case "Time":
		          			//System.out.println(input[1]);
		          			sp.addPath(input[1], input[2], input[3]);
		          			break;
		          		
		          		//Third Case: Shipment --> Adds ShipRoutes to Map so we know where we need to deliver
		          		case "Shipment":
		          			sp.addShipment(input[1], input[2]);
		          			break;
        			}
	        	}
	        	//Once all our inputs are added to the Map, we set our starting City at Sydney, then call the search
	        	ShippingData ship = aStarSearch(sp.m.getNode("Sydney"));
	        	System.out.println("cost = " + ship.getCost());
	        	ship.finalPath(ship.getPath());
		}
	
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		finally {
			if (sc != null) sc.close();
		}
			
	}
	
	/**
	 * Function to add City nodes to the map from the First Case (Refuelling)
	 * @param timeToRefuel
	 * @param nameOfCity
	 */
	private void addCity(String timeToRefuel, String nameOfCity) {
		int refuelTime = Integer.parseInt(timeToRefuel);
		String cityName = nameOfCity;
		City c = new City(cityName, refuelTime);
		this.m.addNode(c);
		
	}
	
	/**
	 * Function to add Route edges to the map from the Second Case (Time)
	 * @param pathTravelTime
	 * @param startCity
	 * @param endCity
	 */
	private void addPath(String pathTravelTime, String startCity, String endCity) {
		int travelTime = Integer.parseInt(pathTravelTime);
		String cityStart = startCity;
		String cityEnd = endCity;

		City s = this.m.getNode(cityStart);
		City e = this.m.getNode(cityEnd);
		e.addRoute(s,  travelTime);
		s.addRoute(e, travelTime);
		
	}
	
	/**
	 * Function to add ShipRoutes to the map from the Third Case (Shipment)
	 * @param originCity
	 * @param destinationCity
	 */
	private void addShipment(String originCity, String destinationCity) {
		City origin = this.m.getNode(originCity);
		City destination = this.m.getNode(destinationCity);
		ShipRoute s = new ShipRoute(origin, destination);
		shipments.add(s);
	}
	
	/**
	 * A* Search function
	 * Oh boy where do I begin
	 * This basically follows the pseudo-code, kind of
	 * Just read through the comments throughout the code for a better understanding I guess
	 * @param start
	 * @return
	 */
	public static ShippingData aStarSearch(City start) {
		//We need a Priority Queue of our Shipping Data. The Priority Queue prioritizes based on Cost
		PriorityQueue<ShippingData> pQueue = new PriorityQueue<ShippingData>();
		//Create our starting ShippingData to chuck into the Priority Queue
		ShippingData sd = new ShippingData(0, shipments, shipments.size());
		//Nodes expanded counter
		int nodes = 0;
		sd.addPath(start);
		pQueue.add(sd);
		//Note: If I had a Heuristic, it would be stored in ShippingData --> change priority queue comparator to look at cost+heuristic cost
		//I mean TECHNICALLY this does have a Heuristic. It's just 0 :D
		//We're essentially looping so until we hit a break point, yay infinite loops!
		while (pQueue.size() != 0) {
			//Pop that node off
			ShippingData current = pQueue.poll();
			//Another loop! This one does end though, when we run out of ShipRoutes (i.e. we finish all our deliveries)
			for (ShipRoute s: current.getShipmentsRemaining()) {
				//Duplicate our ShippingData so that we don't break the loop too early
				ShippingData dupe = new ShippingData(current);
				//The current ShipRoute we are looking at to try and remove
				ShipRoute remove = s;
				//If we are at origin, move to destination, update Shipments counter
				if (s.getOrigin().equals(dupe.getCurrentCity())) {
					City destination = s.getDestination();
					updatePath(dupe, destination);
					remove = s;
					dupe.removeShipments(remove);
					pQueue.add(dupe);
					nodes++;
				}
				//Else, if we are not at the origin, move to the origin, then move to the destination
				else {
					City origin = s.getOrigin();
					City destination = s.getDestination();
					//We need to update the path twice here as we move twice
					updatePath(dupe, origin);
					updatePath(dupe, destination);
					remove = s;
					dupe.removeShipments(remove);
					pQueue.add(dupe);
					nodes = nodes + 2;
				}
				//If we finish all our Shipments!!!
				if (dupe.getCountShipmentsRemaining() == 0) {
					System.out.println(nodes + " nodes expanded");
					return dupe;
				}
				//If we have more shipments left
				else {
					pQueue.add(dupe);
					continue;
				}
			}
		}
		return null;
	}
	
	/**
	 * An attempt to clean up the above code a little with a helper function
	 * @param current
	 * @param destination
	 */
	private static void updatePath(ShippingData current, City destination) {
		current.calculateCost(current.getCurrentCity(),  destination);
		current.addPath(destination);

	}
}

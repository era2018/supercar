import java.util.ArrayList;

public class Graph {
	/*
	public Rider[] sampleLocations = {new Rider("San Francisco", true), new Rider("Livermore", true), new Rider("Los Banos", true),
			new Rider("Santa Clarita", false), new Rider("Los Angeles", false), new Rider("San Diego", false)};
	public double[][] sampleDistances = {{0.0, 44.4, 124.0, 360.0, 394.0, 514.0}, {44.4, 0.0, 79.8, 315.0, 350.0, 470.0},
			{124.0, 79.8, 0.0, 236.0, 270.0, 390.0}, {360.0, 315.0, 236.0, 0.0, 34.2, 155.0}, 
			{394.0, 350.0, 270.0, 34.2, 0.0, 120.0}, {514.0, 470.0, 390.0, 155.0, 120.0, 0.0}
			};*/

	public static final int MAX_CAPACITY = 8;
	public Location[] locationList = new Location[MAX_CAPACITY];
	public double[][] distances = new double[MAX_CAPACITY][MAX_CAPACITY];
	public int totalLocations;
	public int riders;
	
	
	public Graph() //default constructor
	{
		riders = 0;
		totalLocations = 0;
	}
	
	public void addRider(Rider r)
	{
		if(riders == 0)
		{
			locationList[0] = r.startLocation;
			locationList[1] = r.endLocation;
			distances[0][0] = 0.0;
			distances[0][1] = distanceFormula(r.getStartLocation(), r.getEndLocation());
			distances[1][0] = distances[0][1];
			distances[1][1] = 0.0;
			++riders;
			totalLocations += 2;
		}
		else if(riders < MAX_CAPACITY/2)
		{
			locationList[totalLocations] = r.getStartLocation();
			for(int i = 0; i < riders; ++i)
			{
				distances[totalLocations][i] = distanceFormula(r.getStartLocation(), locationList[i]);
				distances[i][totalLocations] = distances[totalLocations][i];
				distances[totalLocations][totalLocations] = 0;
			}
			++totalLocations;
			locationList[totalLocations] = r.getEndLocation();
			for(int i = 0; i < riders; ++i)
			{
				distances[totalLocations][i] = distanceFormula(r.getEndLocation(), locationList[i]);
				distances[i][totalLocations] = distances[totalLocations][i];
				distances[totalLocations][totalLocations] = 0;
			}
			++totalLocations;
			++riders;
		}
		else
		{
			System.out.println("Cannot add more riders");
		}
	}
	
	public class Rider// a wrapper class for locations
	{
		String Name;
		Location startLocation;
		Location endLocation;
		public Rider(String Name, Location startLocation, Location endLocation) 
		{
			this.Name = Name;
			this.startLocation = startLocation;
			this.endLocation = endLocation;
		}
		
		public String getName() {
			return Name;
		}
		
		public Location getStartLocation() {
			return startLocation;
		}
		
		public Location getEndLocation() {
			return endLocation;
		}
	}
	
	public class Location
	{
		private double longitude;
		private double latitude;
		private String locationName;
		private boolean isStartLocation;
		public Location(double longitude, double latitude, String locationName, boolean isStartLocation)
		{
			this.longitude = longitude;
			this.latitude = latitude;
			this.isStartLocation = isStartLocation;
			this.locationName = locationName;
		}
		
		public double getLatitude() {
			return latitude;
		}
		
		public double getLongitude() {
			return longitude;
		}
		
		public String getLocationName() {
			return locationName;
		}
		
		public boolean getIsStartLocation()
		{
			return isStartLocation;
		}
	}
	
	/*
	public void getRoute()
	{
		boolean[] pickup = new boolean[MAX_CAPACITY];
		for(int i = 0; i < locations.length; ++i)
		{
			if(locations[i].getIsStartLocation())
			{
				pickup[i] = true;
			}
			else
			{
				pickup[i] = false;
			}
		}
	}
	
	public void calculateRoute(Rider[] ll, String Location)
	{
		Rider[] newList = new Rider[ll.length - 1];
		
	}*/
	
	public static double distanceFormula(Location start, Location end)
	{
		double longitudeSquared = (start.getLongitude() - end.getLongitude()) * (start.getLongitude() - end.getLongitude());
		double latitudeSquared = (start.getLatitude() - end.getLatitude()) * (start.getLatitude() - end.getLatitude());
		return Math.sqrt(longitudeSquared + latitudeSquared);
	}
	
	
	/*
	public Node hub; // this node connects to all of the other nodes with a 0 distance
	public int numLocations;
	
	
	public Graph() {
		hub = new Node(false, null);
	}
	public void addNode(Node n)
	{
		
	}
	
	public class Node{
		private boolean isStartLocation;
		private String locationName;
		private static final int MAX_CAPACITY = 8;
		public ArrayList<Distance> connectors;
		public Node[] nodes; //this is used for the head node to connect to the other nodes
		public Node(boolean isStartLocation, String locationName)
		{
			this.isStartLocation = isStartLocation;
			this.locationName = locationName;
			nodes = new Node[MAX_CAPACITY];
		}
		
		public boolean isStartLocation() {
			return isStartLocation;
		}
		
		public void addDistance(Node connector, double distance)
		{
			connectors.add(new Distance(connector, distance));
		}
		
		public String getLocationName() {
			return locationName;
		}
		
		public String toString()
		{
			if(isStartLocation)
			{
				return "pickup" + locationName;
			}
			else
			{
				return "dropoff" + locationName;
			}
		}
	}
	
	class Distance{
		private Node connector;
		private double distance;
		public Distance(Node connector, double distance)
		{
			this.connector = connector;
			this.distance = distance;
		}
	}*/
	/*
	public double getDistance(String locationA, String locationB)
	{
		/*
		String[] locations = {"San Francisco", "Livermore", "Los Banos", "Santa Clarita", 
				"Los Angeles", "San Diego"};
		double[][] distances = {{0.0, 44.4, 124.0, 360.0, 394.0, 514.0}, {44.4, 0.0, 79.8, 315.0, 350.0, 470.0},
		{124.0, 79.8, 0.0, 236.0, 270.0, 390.0}, {360.0, 315.0, 236.0, 0.0, 34.2, 155.0}, 
		{394.0, 350.0, 270.0, 34.2, 0.0, 120.0}, {514.0, 470.0, 390.0, 155.0, 120.0, 0.0}
		};
		int i = 0;
		int j = 0;
		for(;i < sampleLocations.length; ++i)
		{
			if(locationA.equalsIgnoreCase(sampleLocations[i].getLocation()))
			{
				break;
			}
		}
		
		for(;j < sampleLocations.length; ++j)
		{
			if(locationB.equalsIgnoreCase(sampleLocations[j].location))
			{
				break;
			}
		}
		
		if(i == sampleLocations.length || j == sampleLocations.length)
		{
			System.out.println("invalid location");
		}
		
		return sampleDistances[i][j];
		
		/*
		if(locationA.equalsIgnoreCase("San Francisco"))
		{
			switch(locationB)
			{
			case "San Francisco": return 0.0;
			case "Livermore": return 44.4;
			case "Los Banos": return 124.0;
			case "Santa Clarita": return 360.0;
			case "Los Angeles": return 394.0;
			case "San Diego": return 514.0;
			
			}
		}
		if(locationA.equalsIgnoreCase("Livermore"))
		{
			switch(locationB)
			{
			case "San Francisco": return 44.4;
			case "Livermore": return 0.0;
			case "Los Banos": return 79.8;
			case "Santa Clarita": return 315.0;
			case "Los Angeles": return 350.0;
			case "San Diego": return 470.0;
			
			}
		}
		if(locationA.equalsIgnoreCase("Los Banos"))
		{
			switch(locationB)
			{
			case "San Francisco": return 124.0;
			case "Livermore": return 79.8;
			case "Los Banos": return 0.0;
			case "Santa Clarita": return 236.0;
			case "Los Angeles": return 270.0;
			case "San Diego": return 0.0;
			
			}
		}
		if(locationA.equalsIgnoreCase("Santa Clarita"))
		{
			switch(locationB)
			{
			case "San Francisco": return 360.0;
			case "Livermore": return 315.0;
			case "Los Banos": return 236.0;
			case "Santa Clarita": return 0.0;
			case "Los Angeles": return 0.0;
			case "San Diego": return 0.0;
			
			}
		}
		if(locationA.equalsIgnoreCase("Los Angeles"))
		{
			switch(locationB)
			{
			case "San Francisco": return 394.0;
			case "Livermore": return 350.0;
			case "Los Banos": return 270.0;
			case "Santa Clarita": return 0.0;
			case "Los Angeles": return 0.0;
			case "San Diego": return 0.0;
			
			}
		}
		if(locationA.equalsIgnoreCase("San Diego"))
		{
			switch(locationB)
			{
			case "San Francisco": return 514.0;
			case "Livermore": return 470.0;
			case "Los Banos": return 0.0;
			case "Santa Clarita": return 0.0;
			case "Los Angeles": return 0.0;
			case "San Diego": return 0.0;
			
			}
		}
	}*/
}

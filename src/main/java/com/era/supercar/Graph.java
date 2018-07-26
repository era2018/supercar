package com.era.supercar;

public class Graph {
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
	
	public static double distanceFormula(Location start, Location end)
	{
		double longitudeSquared = (start.getLongitude() - end.getLongitude()) * (start.getLongitude() - end.getLongitude());
		double latitudeSquared = (start.getLatitude() - end.getLatitude()) * (start.getLatitude() - end.getLatitude());
		return Math.sqrt(longitudeSquared + latitudeSquared);
	}
}

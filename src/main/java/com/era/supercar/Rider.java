package com.era.supercar;

//import Graph.Location;

public class Rider {
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

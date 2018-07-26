package com.era.supercar;

public class Location {
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

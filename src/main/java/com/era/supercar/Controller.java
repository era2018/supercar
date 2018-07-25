package com.era.supercar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Controller
{

    private PreparedStatement preparedStatement;
	private Statement pstatement;
	private Connection connection;
	
	public Controller()
	{
		System.out.println("Controller construction");
        Properties props = new Properties();
        props.put("User", "ubdb");
        props.put("Password", "123456");

        try {
            this.connection = DriverManager.getConnection("jdbc:sybase:Tds:10.171.1.252:2638/ubdatabase", props);
			this.pstatement = this.connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
	}
	
	@CrossOrigin
	@PostMapping(path = "/login")
	public String login(Credentials creds)
	{
		System.out.println(creds.getEmail() + " " + creds.getPass());
		return "done";
	}


	@CrossOrigin
	@GetMapping("/event")
	public Event getEvent()
	{
		try {
			String queryString = "SELECT TOP 1 * from EventLog ORDER BY logTime DESC";
			ResultSet rs = pstatement.executeQuery(queryString);
			rs.next();
			//System.out.println(rs.getInt("eventID") + " " + rs.getString("logTime"));
			Event ev = new Event(rs.getInt("eventID"), rs.getString("carID"), rs.getString("logTime"));
			rs.close();
			//Event ev = new Event(1, "2", "3");
			return ev;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	@CrossOrigin
	@PostMapping(path = "/rides")
	public ArrayList<RideRequest> getRides()
	{
	    ArrayList<RideRequest> rides = new ArrayList<RideRequest>();
		//System.out.println(ride.getName());
		try {
			String queryString = "SELECT * from RideSharing";
			ResultSet rs = pstatement.executeQuery(queryString);
			while(rs.next()) {
               RideRequest ride = new RideRequest(rs.getString("name"), rs.getString("startCity"), rs.getString("endCity"), rs.getString("startState"), rs.getString("endState"), rs.getString("startTime"), rs.getString("ageRange"));
               rides.add(ride);
               //System.out.println("" +  rs.getInt("timestamp")+", "+rs.getInt("percent")+", "+ rs.getInt("absolute"));
            }
			//System.out.println(rs.getInt("eventID") + " " + rs.getString("logTime"));
			rs.close();
			//Event ev = new Event(1, "2", "3");
			return rides;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rides;
	}
	*/
	
	@CrossOrigin
	@PostMapping(path = "/ride")
	public String ride(RideRequest ride)
	{
		//System.out.println(ride.getName());
		try {
			//String queryString = "INSERT INTO RideSharing (startTime, startState, startCity, name, endState, endCity, ageRange) values (?, ?, ?, ?, ?, ?, ?)";
			String queryString = "INSERT INTO RideSharing (startCity, name, endCity, startTime, ageRange, startState, endState) values (?, ?, ?, ?, ?, ?, ?)";
			this.preparedStatement = this.connection.prepareStatement(queryString);
			
			System.out.println(ride);

			this.preparedStatement.setString(1, ride.getCity());
            this.preparedStatement.setString(2, ride.getName());
            this.preparedStatement.setString(3, ride.getCity2());
            this.preparedStatement.setString(4, ride.getDate());
            this.preparedStatement.setString(5, ride.getRange());
        	this.preparedStatement.setString(6, ride.getState());
        	this.preparedStatement.setString(7, ride.getState2());
        	this.preparedStatement.executeUpdate();
			//System.out.println(rs.getInt("eventID") + " " + rs.getString("logTime"));
			//Event ev = new Event(rs.getInt("eventID"), rs.getString("carID"), rs.getString("logTime"));
			//rs.close();
			//Event ev = new Event(1, "2", "3");
			return "done";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "done";
	}
}

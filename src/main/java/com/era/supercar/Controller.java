package com.era.supercar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Controller
{

	private Statement pstatement;
	
	public Controller()
	{
		System.out.println("Controller construction");
        Properties props = new Properties();
        props.put("User", "ubdb");
        props.put("Password", "123456");
        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:sybase:Tds:10.171.1.252:2638/ubdatabase", props);
			this.pstatement = con.createStatement();
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

	@CrossOrigin
	@PostMapping(path = "/ride")
	public String ride(RideRequest ride)
	{
		System.out.println(ride.getName());
		return "done";
	}
}

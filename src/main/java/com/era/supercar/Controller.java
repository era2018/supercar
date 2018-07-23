package com.era.supercar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Controller
{
    @GetMapping("/events")
    public List<Event> getEvents()
    {
        Properties props = new Properties();
        props.put("User", "ubdb");
        props.put("Password", "123456");
        Connection con = null;

        ArrayList<Event> events = new ArrayList<Event>();

        try {
            con = DriverManager.getConnection("jdbc:sybase:Tds:10.171.1.252:2638/ubdatabase", props);
			String queryString = "select * from EventLog";
			Statement  pstatement = con.createStatement();
			ResultSet rs = pstatement.executeQuery(queryString );
			
			while (rs.next()) {
                //Event ev = new Event(rs.getLong("eventID"));
                Event ev = new Event(rs.getInt("eventID"), rs.getString("carID"), rs.getString("logTime"));
			    events.add(ev);
			}
			pstatement.close();
			con.close();
            rs.close();
            
            return events;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
        
        return events;
    }
}


/*

public Event pull() {
		Properties props = new Properties();
		props.put( "User", "ubdb" );
		props.put( "Password", "123456" );
		Connection con = null;

		try {
			System.out.println("connecting");
			con = DriverManager.getConnection("jdbc:sybase:Tds:10.171.1.252:2638/ubdatabase", props);
			System.out.println("connected");
			String queryString = "select * from EventLog";
			Statement  pstatement = con.createStatement();
			ResultSet rs = pstatement.executeQuery(queryString );
			
			System.out.println("quried.");
			
			while (rs.next()) {
				Event ev = new Event(rs.getLong("eventID"));
				bag.addElement(ev);
			}
			pstatement.close();
			con.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
		
    } 
    
    */
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

    @GetMapping("/events")
    public List<Event> getEvents()
    {
		ArrayList<Event> events = new ArrayList<Event>();

        try {
			String queryString = "select * from EventLog";
			ResultSet rs = pstatement.executeQuery(queryString);
			
			while (rs.next()) {
                Event ev = new Event(rs.getInt("eventID"), rs.getString("carID"), rs.getString("logTime"));
			    events.add(ev);
			}
            rs.close();
            
            return events;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
        
        return events;
    }
}

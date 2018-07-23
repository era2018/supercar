package com.era.supercar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

class ServerWorker implements Runnable
{
	public static Vector<Event> bag = new Vector<Event>();
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
	private void push(int g, long x, int y, int z) {
		String jsonData;

		DummyStream data = new DummyStream();

        try {
			while((jsonData=data.readLine()) != null)
			{
			    // Parse JSON
			    JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();

			    String percent = jsonObject.get("percent").getAsString();
			    
			    
			    System.out.println(jsonData);
				System.out.println(percent);
				
				// Send data to database
				Properties props = new Properties();
				props.put( "User", "ubdb" );
				props.put( "Password", "123456" );
				System.out.println("connecting");
				Connection con = DriverManager.getConnection("jdbc:sybase:Tds:10.171.1.252:2638/ubdatabase", props);
				System.out.println("connected");
				String queryString = "INSERT INTO sensordata (ID, timestamp, percent, absolute) values ("+g+","+x+","+y+","+z+")";
				PreparedStatement pstatement = con.prepareStatement(queryString);
				pstatement.executeUpdate();
		
				System.out.println("Insert executed.");
				pstatement.close();
				con.close();
    

            }
            System.out.println("ended");
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		//push(100, 1, 2, 3);
		pull();
		/*
		CloudBit data = new CloudBit();

        BufferedReader dataStream = new BufferedReader(
			new InputStreamReader(data.getInputStream()));
		*/

        
    }
}
package com.era.supercar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

class ServerWorker implements Runnable
{
	public void run() {

		/*
		CloudBit data = new CloudBit();

        BufferedReader dataStream = new BufferedReader(
			new InputStreamReader(data.getInputStream()));
		*/

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
				String queryString = "INSERT INTO sensordata (timestamp, percent, absolute) values (0, 0, 0)";
				PreparedStatement pstatement = ((Connection) con).prepareStatement(queryString);
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
}
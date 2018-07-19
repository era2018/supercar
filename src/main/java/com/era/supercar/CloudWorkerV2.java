package com.era.supercar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class CloudWorker implements Runnable
{
	public void run() {
            // Connect to cloud server
            String url = "https://api-http.littlebitscloud.cc/v2/devices/243c201f7957/input";
            
            try {
                URL obj = new URL(url);
                HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();
                connection.setRequestMethod("POST");

                connection.setRequestProperty("Authorization", "Bearer 9fd40b2075c1ec9ae77b254cad78377a54cef4401cd5faceab60de802ec7eee7");
    
                connection.setDoOutput(true);
    
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
    
                String inputLine;
    
                while((inputLine=in.readLine()) != null)
                {
                    // Parse JSON
                    inputLine = inputLine.substring(5);
                    JsonObject jsonObject = new JsonParser().parse(inputLine).getAsJsonObject();

                    String percent = jsonObject.get("percent").getAsString();

                    //System.out.println(inputLine);
                    System.out.println(percent);
    
                    // Send data to database
                    Properties props = new Properties();
                    props.put( "User", "ubdb" );
                    props.put( "Password", "123456" );
                    System.out.println("connecting");
                    Connection connection = DriverManager.getConnection("jdbc:sybase:Tds:10.171.1.252:2638/ubdatabase", props);
                    System.out.println("connected");
                    String queryString = "INSERT INTO sensordata (timestamp, percent, absolute) values (0, 0, 0)";
                    PreparedStatement pstatement = connection.prepareStatement(queryString);
                    pstatement.executeUpdate();
           
                    System.out.println("Insert executed.");
                    pstatement.close();
                    connection.close();
    
                }
			} catch (MalformedURLException e) {
				e.printStackTrace();
            } catch (IOException e) {
				e.printStackTrace();
            }
            catch (Exception e) {
				e.printStackTrace();
            }
        }
}
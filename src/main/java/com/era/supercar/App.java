package com.era.supercar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );

        // Connect to cloud server
        String url = "https://api-http.littlebitscloud.cc/v2/devices/243c201f7957/input";
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
            System.out.println(inputLine);

            // Send data to database
            // TODO

        }
    }
}
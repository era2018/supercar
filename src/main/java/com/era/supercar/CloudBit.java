package com.era.supercar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

class CloudBit
{

    private InputStream in;

    CloudBit()
    {
        // Connect to cloud server
        String url = "https://api-http.littlebitscloud.cc/v2/devices/243c201f7957/input";
            
        try {
            URL obj = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer 9fd40b2075c1ec9ae77b254cad78377a54cef4401cd5faceab60de802ec7eee7");
            connection.setDoOutput(true);

            this.in = connection.getInputStream();
        } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found catch");
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally{}
    }
    
    public InputStream getInputStream()
    {
        return this.in;
    }
    
}
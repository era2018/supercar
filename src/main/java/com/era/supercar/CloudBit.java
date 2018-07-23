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

    private BufferedReader in;

    public String readLine() throws IOException
    {
        String line = this.in.readLine();
        if( line.trim().length() == 0)
        {
            line = this.in.readLine();
        }

        return line.substring(5);
    }

    CloudBit()
    {
        // Connect to cloud server
        String url = "https://api-http.littlebitscloud.cc/v2/devices/243c201f7957/input";
        int timeout = 0;

        // Sometimes fails for no reason. Try a few times before giving up.
        while(timeout < 5)
        {
            try {
                URL obj = new URL(url);
                HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();
    
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Authorization", "Bearer 9fd40b2075c1ec9ae77b254cad78377a54cef4401cd5faceab60de802ec7eee7");
                connection.setDoOutput(true);
    
                this.in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                return;
            } catch (IOException e) {
                timeout += 1;
            }
        }

        throw new RuntimeException("Couldn't connect to CloudBit");
    }    
}
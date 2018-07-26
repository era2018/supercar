package com.era.supercar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    CloudBit(String id, String authorization)
    {
        // Connect to cloud server
        String url = String.format("https://api-http.littlebitscloud.cc/v2/devices/%s/input", id);
        int timeout = 0;

        // Sometimes fails for no reason. Try a few times before giving up.
        while(timeout < 5)
        {
            try {
                URL obj = new URL(url);
                HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();
    
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Authorization", String.format("Bearer %s", authorization));
                connection.setDoOutput(true);
    
                this.in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                return;
            } catch (IOException e) {
                timeout += 1;
            }
        }

        try{throw new Exception(String.format("Couldn't connect to CloudBit %s", id));}
        catch(Exception e) {e.printStackTrace();}
    }    
}
package com.era.supercar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

class ServerWorker implements Runnable
{
	public void run() {

        //CloudBit cloud = new CloudBit();
        //DummyDataStream data = new DummyDataStream();
        //DummyStream data = new DummyStream();

        //BufferedReader dataStream = new BufferedReader(
        //    new InputStreamReader(data.getInputStream()));
        DummyStream dataStream = new DummyStream();

        String jsonData;

        try {
			while((jsonData=dataStream.readLine()) != null)
			{
			    // Parse JSON
			    //jsonData = jsonData.substring(5);
			    JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();

			    String percent = jsonObject.get("percent").getAsString();

			    System.out.println(jsonData);
			    System.out.println(percent);

			    // Send data to database
			    // TODO

            }
            System.out.println("ended");
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
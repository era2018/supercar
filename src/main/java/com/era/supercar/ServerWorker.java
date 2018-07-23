package com.era.supercar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

class ServerWorker implements Runnable
{
	public void run() {

		CloudBit data = new CloudBit();

        BufferedReader dataStream = new BufferedReader(
			new InputStreamReader(data.getInputStream()));

        String jsonData;

		//DummyStream data = new DummyStream();

		//try(DBConnection connection = new DBConnection())
		{
			try {
				while((jsonData=dataStream.readLine()) != null)
				{
					System.out.println(jsonData);

				    // Parse JSON
				    JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();

					int value = jsonObject.get("absolute").getAsInt();
					
					// Codes are in 170 wide windows
					// Signal isn't clear, but hopfully won't varry by more than 43?
					value = (value + 43) % 170;

					long timestamp = jsonObject.get("timestamp").getAsLong();
					String iso = DateTimeFormatter.ofPattern("yyyyMMddHHmmssnX")
					.withZone(ZoneOffset.UTC)
					.format(Instant.ofEpochMilli(timestamp));

					String id = jsonObject.getAsJsonObject("from").getAsJsonObject("device").get("id").getAsString();

					System.out.println(iso);
					
					//connection.insert(value, id, timestamp);
				}
			} catch (JsonSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//catch(SQLException e)
		{
		//	e.printStackTrace();
		//	System.exit(-1);
		}
    }
}
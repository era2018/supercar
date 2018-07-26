package com.era.supercar;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

class ServerWorker implements Runnable
{
	private DBConnection connection;

	public ServerWorker(DBConnection connection)
	{
		this.connection = connection;
	}

	public void run() {

		CloudBit data = new CloudBit("243c201f7957", "9fd40b2075c1ec9ae77b254cad78377a54cef4401cd5faceab60de802ec7eee7");

		String jsonData;
		
		try {
			while((jsonData=data.readLine()) != null)
			{
				// Parse JSON
				JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();

				int value = jsonObject.get("absolute").getAsInt();
				int eventCode;
				
				// Codes are in 200 wide windows
				if( value <= 5 )
				{
					eventCode = 0;
				}
				else
				{
					eventCode = ((value - 5)/200)+1;
				}
				
				if( eventCode > 5) eventCode = 5;

				long timestamp = jsonObject.get("timestamp").getAsLong();
				String iso = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
				.withZone(ZoneOffset.UTC)
				.format(Instant.ofEpochMilli(timestamp));

				String id = jsonObject.getAsJsonObject("from").getAsJsonObject("device").get("id").getAsString();
				
				this.connection.insert(eventCode, id, iso);
			}
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
    }
}
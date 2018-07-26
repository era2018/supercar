package com.era.supercar;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class ServerWorker implements Runnable
{
	private DBConnection connection;

	public ServerWorker(DBConnection connection)
	{
		this.connection = connection;
	}

	public Event alertSignal(String data)
	{
		JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
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
		return new Event(eventCode, id, iso);
	}

	public Event rideShareSignal(String data)
	{JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();

		int value = jsonObject.get("absolute").getAsInt();
		int eventCode;
		
		eventCode = ((value-5)/200)+1;

		if( eventCode < 4)
		{
			eventCode = 0;
		}
		else if (eventCode > 5)
		{
			eventCode = 5;
		}
		
		long timestamp = jsonObject.get("timestamp").getAsLong();
		String iso = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
		.withZone(ZoneOffset.UTC)
		.format(Instant.ofEpochMilli(timestamp));

		String id = jsonObject.getAsJsonObject("from").getAsJsonObject("device").get("id").getAsString();
		return new Event(eventCode, id, iso);
	}

	public void run()
	{
		CloudBit alertBit = new CloudBit("243c201f7957", "9fd40b2075c1ec9ae77b254cad78377a54cef4401cd5faceab60de802ec7eee7");
		CloudBit rideShareBit = new CloudBit("243c200cd228", "a71f56b251451812f6093620e4411a10ecd0e6cbcb3c08db57929dc68627e7b2");

		while(true)
		{
			try {
				Event alert = alertSignal(alertBit.readLine());
				Event rideStatus = rideShareSignal(rideShareBit.readLine());

				int finalEventCode = rideStatus.getEventCode();
				String finalTimestamp = rideStatus.getTimestamp();
				if( finalEventCode == 0)
				{
					finalEventCode = alert.getEventCode();
					finalTimestamp = alert.getTimestamp();
				}

				this.connection.insert(finalEventCode, alert.getCarID(), finalTimestamp);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}
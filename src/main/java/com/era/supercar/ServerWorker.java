package com.era.supercar;

import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

		try(DBConnection connection = new DBConnection())
		{
			while((jsonData=data.readLine()) != null)
			{
				System.out.println(jsonData);

			    // Parse JSON
			    JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();

				int value = jsonObject.get("absolute").getAsInt();

				long timestamp = jsonObject.get("timestamp").getAsLong();
				String iso = DateTimeFormatter.ofPattern("yyyyMMddHHmmssnX")
				.withZone(ZoneOffset.UTC)
				.format(Instant.ofEpochMilli(timestamp));

				String id = jsonObject.getAsJsonObject("from").getAsJsonObject("device").get("id").getAsString();

				System.out.println(iso);
				
				connection.insert(value, id, timestamp);
            }
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
    }
}
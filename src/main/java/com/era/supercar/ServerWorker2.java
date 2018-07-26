package com.era.supercar;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

class ServerWorker2 implements Runnable
{
    private DBConnection connection;

	public ServerWorker2(DBConnection connection)
	{
		this.connection = connection;
	}


	public void run() {

		CloudBit data = new CloudBit("243c200cd228", "a71f56b251451812f6093620e4411a10ecd0e6cbcb3c08db57929dc68627e7b2");
		
        String jsonData;

        try {
            while((jsonData=data.readLine()) != null)
            {
                // Parse JSON
                JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();

                int value = jsonObject.get("absolute").getAsInt();
                int eventCode;
                
                eventCode = ((value-5)/200)+1;

                if( eventCode < 4)
                {
                    return; // don't send anything;
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
package com.era.supercar;

import com.google.gson.JsonObject;

class DummyStream
{
  private long timestamp;
  private int absolute;

  private static final int ABSOLUTE_MAX = 1023;

  public DummyStream()
  {
    this.timestamp = 0;
    this.absolute = 0;
  }

  public String readLine()
  {
    JsonObject dummy = new JsonObject();

    dummy.addProperty("timestamp", this.timestamp);
    dummy.addProperty("absolute", this.absolute);
    dummy.addProperty("percent", this.absolute/ABSOLUTE_MAX);

    JsonObject from = new JsonObject();
    JsonObject device = new JsonObject();

    device.addProperty("id", "243c201f7957");

    from.add("device", device);

    dummy.add("from", from);

    this.timestamp++;
    this.absolute++;
    
    return dummy.toString();
  }
}
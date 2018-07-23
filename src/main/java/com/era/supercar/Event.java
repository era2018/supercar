package com.era.supercar;

class Event
{
    private int eventCode;
    private String carID;
    private String timestamp;

    public Event(int eventCode, String carID, String timestamp)
    {
        this.carID = carID;
        this.eventCode = eventCode;
        this.timestamp = timestamp;
    }

    public int getEventCode()
    {
        return this.eventCode;
    }

    public String getTimestamp()
    {
        return this.timestamp;
    }

    public String getCarID()
    {
        return this.carID;
    }
}
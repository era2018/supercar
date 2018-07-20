package com.era.supercar;

class Event
{
    private int carID;
    private int eventCode;
    private long timestamp;

    public Event(int carID, int eventCode, long timestamp)
    {
        this.carID = carID;
        this.eventCode = eventCode;
        this.timestamp = timestamp;
    }

    public int getEventCode()
    {
        return this.eventCode;
    }

    public long getTimestamp()
    {
        return this.timestamp;
    }

    public int getCarID()
    {
        return this.carID;
    }
}
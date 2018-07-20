package com.era.supercar;

class Event
{
    private int eventCode;
    private long timestamp;

    public Event(int eventCode, long timestamp)
    {
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
}
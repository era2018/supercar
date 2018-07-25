package com.era.supercar;

class RideRequest
{
    private String name;
    private String address;
    private String address2;
    private String city;
    private String city2;
    private String state;
    private String state2;
    private String zip;
    private String zip2;
    private String age;
    private String date;
    private String range;
    private String time;


    /*
    public RideRequest(String name, String city, String city2,
    String state, String state2, String date, String range)
    {
        this.name = name;
        this.address = "";
        this.address2 = "";
        this.city = city;
        this.city2 = city2;
        this.state = state;
        this.state2 = state2;
        this.zip = "";
        this.zip2 = "";
        this.age = "";
        this.date = date;
        this.range = range;
        this.time = "";
    }
    */
    
    public RideRequest(String name, String address, 
    String address2, String city, String city2,
    String state, String state2, String zip, String zip2,
    String age, String date, String range, String time)
    {
        this.name = name;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.city2 = city2;
        this.state = state;
        this.state2 = state2;
        this.zip = zip;
        this.zip2 = zip2;
        this.age = age;
        this.date = date;
        this.range = range;
        this.time = time;
    }

    public String getName()
    {
        return this.name;
    }

    public String getAge()
    {
        return this.age;
    }

    public String getTime()
    {
        return this.time;
    }

    public String getAddress()
    {
        return this.address;
    }

    public String getAddress2()
    {
        return this.address2;
    }

    public String getCity()
    {
        return this.city;
    }

    public String getCity2()
    {
        return this.city2;
    }

    public String getState()
    {
        return this.state;
    }

    public String getState2()
    {
        return this.state2;
    }

    public String getRange()
    {
        return this.range;
    }

    public String getZip()
    {
        return this.zip;
    }

    public String getZip2()
    {
        return this.zip2;
    }

    public String getDate()
    {
        return this.date;
    }

}